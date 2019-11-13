package gov.nyc.doitt.jobstatusmanager.domain.jobappconfig;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import gov.nyc.doitt.jobstatusmanager.domain.jobstatus.model.JobStatus;
import gov.nyc.doitt.jobstatusmanager.domain.jobstatus.model.JobStatusType;

@Repository
interface JobAppConfigRepository extends JpaRepository<JobStatus, Integer> {

	@Lock(LockModeType.PESSIMISTIC_READ)
	List<JobStatus> findByStatusInAndErrorCountLessThan(List<JobStatusType> statuses, int errorCount,
			Pageable pageable);

}