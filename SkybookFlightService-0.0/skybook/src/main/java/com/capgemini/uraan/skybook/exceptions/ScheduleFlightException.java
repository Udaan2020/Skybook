package com.capgemini.uraan.skybook.exceptions;

public class ScheduleFlightException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6103456017776167142L;

	public ScheduleFlightException() {
		
	}

	public ScheduleFlightException(String message) {
		super(message);
		
	}

	public ScheduleFlightException(Throwable cause) {
		super(cause);
		
	}

	public ScheduleFlightException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ScheduleFlightException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
