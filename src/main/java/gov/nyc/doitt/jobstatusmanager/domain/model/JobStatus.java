package gov.nyc.doitt.jobstatusmanager.domain.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "JOB_STATUS")
public class JobStatus {

	@Id
	@Column(name = "ID")
	private long id;

	@Column(name = "APP_ID")
	private String appId;

	@Column(name = "JOB_ID")
	private String jobId;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "JOB_CREATED")
	private Timestamp jobCreated;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private JobStatusType status;

	@Column(name = "START_TIMESTAMP")
	private Timestamp startTimestamp;

	@Column(name = "END_TIMESTAMP")
	private Timestamp endTimestamp;

	@Column(name = "ERROR_COUNT")
	private int errorCount;

	@Version
	@Column(name = "MULTI_INSTANCE_CTRL")
	private int multiInstanceCtrl;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getJobCreated() {
		return jobCreated;
	}

	public void setJobCreated(Timestamp jobCreated) {
		this.jobCreated = jobCreated;
	}

	public JobStatusType getStatus() {
		return status;
	}

	public void setStatus(JobStatusType status) {
		this.status = status;
	}

	public Timestamp getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(Timestamp startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public Timestamp getEndTimestamp() {
		return endTimestamp;
	}

	public void setEndTimestamp(Timestamp endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	public int getMultiInstanceCtrl() {
		return multiInstanceCtrl;
	}

	public void setMultiInstanceCtrl(int multiInstanceCtrl) {
		this.multiInstanceCtrl = multiInstanceCtrl;
	}

	@Override
	public String toString() {
		return "JobStatus [id=" + id + ", appId=" + appId + ", jobId=" + jobId + ", description=" + description
				+ ", status=" + status + ", startTimestamp=" + startTimestamp + ", endTimestamp=" + endTimestamp
				+ ", errorCount=" + errorCount + ", multiInstanceCtrl=" + multiInstanceCtrl + "]";
	}

}
