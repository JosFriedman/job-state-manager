package gov.nyc.doitt.jobstatemanager.jobconfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class JobConfigDtoMockerUpper {

	public String jobName = "myJob1";

	public List<JobConfigDto> createList(int listSize) throws Exception {

		List<JobConfigDto> jobs = new ArrayList<>();
		for (int i = 0; i < listSize; i++) {
			jobs.add(create(jobName + i));
		}
		return jobs;
	}

	public JobConfigDto create() throws Exception {

		int i = new Random().nextInt(100) * -1;
		return create(jobName + i);
	}

	public JobConfigDto create(String jobName) throws Exception {

		JobConfigDto jobConfigDto = new JobConfigDto();

		jobConfigDto.setJobName(jobName);
		jobConfigDto.setDescription("description" + jobName);
		jobConfigDto.setNotifyEmail("josfriedman@doitt.nyc.gov");

		ArrayList<TaskConfigDto> taskConfigDtos = new ArrayList<>();
		for (int j = 0; j < 3; j++) {

			TaskConfigDto taskConfigDto = new TaskConfigDto();
			taskConfigDto.setName("name" + j);
			taskConfigDto.setDescription("description" + j);
			taskConfigDto.setMaxBatchSize(j + 50);
			taskConfigDto.setMaxRetriesForError(j);
			taskConfigDtos.add(taskConfigDto);
		}
		jobConfigDto.setTaskConfigDtos(taskConfigDtos);

		return jobConfigDto;
	}
}
