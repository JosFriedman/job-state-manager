package gov.nyc.doitt.jobstatusmanager.domain.jobstatus;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gov.nyc.doitt.jobstatusmanager.domain.jobstatus.model.JobStatus;
import gov.nyc.doitt.jobstatusmanager.domain.jobstatus.model.JobStatusType;

@Component
public class JobStatusService {

	private Logger logger = LoggerFactory.getLogger(JobStatusService.class);

	@Autowired
	private JobStatusRepository jobStatusRepository;

	@Value("${jobstatusmanager.domain.jobstatus.JobStatusService.maxBatchSize}")
	private int maxBatchSize;

	@Value("${jobstatusmanager.domain.jobstatus.JobStatusService.maxRetriesForError}")
	private int maxRetriesForError;

	private PageRequest pageRequest;

	@PostConstruct
	private void postConstruct() {
		pageRequest = PageRequest.of(0, maxBatchSize, Sort.by(Sort.Direction.ASC, "jobCreated"));
	}

	@Transactional(transactionManager = "jobStatusManagerTransactionManager")
	public List<JobStatus> getAll()  { 
		return jobStatusRepository.findAll();
	}

	/**
	 * Return next batch of submissions
	 * 
	 * @return
	 */
	@Transactional(transactionManager = "jobStatusManagerTransactionManager")
	public List<JobStatus> getNextBatch()  { 

		try {
			List<JobStatus> jobStatuses = jobStatusRepository
					.findByStatusInAndErrorCountLessThan(
							Arrays.asList(new JobStatusType[]{JobStatusType.NEW, JobStatusType.ERROR}),
							maxRetriesForError + 1, pageRequest);
			logger.info("getNextBatch: number of submissions found: {}", jobStatuses.size());

			// mark each submission as picked up for processing
			jobStatuses.forEach(p -> {
				p.setStatus(JobStatusType.PROCESSING);
				p.setStartTimestamp(new Timestamp(System.currentTimeMillis()));
				updateJobStatus(p);
			});
			return jobStatuses;

		} catch (Exception e) {
			throw new JobStatusManagerConcurrencyException(e);
		}

	}

	@Transactional("jobStatusManagerTransactionManager")
	public JobStatus createJobStatus(JobStatus jobStatus) {

		jobStatus.setStatus(JobStatusType.NEW);
		jobStatusRepository.save(jobStatus);
		return jobStatus;
	}

	@Transactional("jobStatusManagerTransactionManager")
	public void updateJobStatus(JobStatus jobStatus) {

		jobStatusRepository.save(jobStatus);
	}

}
