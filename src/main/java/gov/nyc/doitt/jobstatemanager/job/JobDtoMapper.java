package gov.nyc.doitt.jobstatemanager.job;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * Map Job to and from JobDto
 * 
 */
@Component
class JobDtoMapper {

	private ModelMapper modelMapper = new ModelMapper();

	private PropertyMap<JobDto, Job> jobDtoPropertyMap = new PropertyMap<JobDto, Job>() {

		protected void configure() {
			skip(destination.get_id());
			skip(destination.getAppName());
			skip(destination.getCreatedTimestamp());
			skip(destination.getState());
		}
	};

	public JobDtoMapper() {

		modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(AccessLevel.PRIVATE);
		modelMapper.addMappings(jobDtoPropertyMap);
	}

	public Job fromDto(String appName, JobDto jobDto) {

		Job job =  modelMapper.map(jobDto, Job.class);
		job.setAppName(appName);
		return job;
	}

	public Job fromDto(JobDto jobDto, Job job) {

		modelMapper.map(jobDto, job);
		return job;
	}

//	public Job fromDtoResult(JobDto jobDto, Job job) {
//
//		JobState state = JobState.valueOf(jobDto.getState());
//		if (state == JobState.ERROR) {
//			if (Boolean.TRUE == jobDto.isReset()) {
//				job.resetWithError();
//			} else {
//				job.endWithError(jobDto.getErrorReason());
//			}
//		} else if (state == JobState.COMPLETED) {
//			job.endWithSuccess();
//		} else {
//			throw new JobStateManagerException("Unsupported state for result: " +  state);
//		}
//		return job;
//	}

	public List<JobDto> toDto(List<Job> jobs) {

		if (CollectionUtils.isEmpty(jobs))
			return new ArrayList<JobDto>();
		return jobs.stream().map(p -> toDto(p)).collect(Collectors.toList());
	}

	public JobDto toDto(Job job) {

		return modelMapper.map(job, JobDto.class);
	}

}
