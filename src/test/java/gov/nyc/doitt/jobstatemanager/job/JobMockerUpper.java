package gov.nyc.doitt.jobstatemanager.job;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.stereotype.Component;

@Component
public class JobMockerUpper {

	public String jobName = "myJob";

	public List<Job> createList(int listSize) throws Exception {

		List<Job> jobs = new ArrayList<>();
		for (int i = 0; i < listSize; i++) {
			jobs.add(create(i));
		}
		return jobs;
	}

	public Job create() throws Exception {

		int i = new Random().nextInt(100) * -1;
		return create(i);
	}

	public Job create(int idx) throws Exception {

		Job job = new Job();

		FieldUtils.writeField(job, "jobName", jobName, true);
		FieldUtils.writeField(job, "jobId", "jobId" + idx, true);
		FieldUtils.writeField(job, "description", "description" + idx, true);
		FieldUtils.writeField(job, "nextTaskName", "nextTaskName" + idx, true);

		// make very old so it is found first
		FieldUtils.writeField(job, "createdTimestamp", new Timestamp(System.currentTimeMillis() - 9000000000000L + idx), true);

		return job;
	}
}
