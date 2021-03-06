package gov.nyc.doitt.jobstatemanager.common;

import org.springframework.http.HttpStatus;

public class ConflictException extends JobStateManagerException {

	private static final long serialVersionUID = 1L;

	public ConflictException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConflictException(String message) {
		super(message);
	}

	public ConflictException(Throwable cause) {
		super(cause);
	}

	@Override
	protected HttpStatus getHttpStatus() {
		return HttpStatus.CONFLICT;
	}

}