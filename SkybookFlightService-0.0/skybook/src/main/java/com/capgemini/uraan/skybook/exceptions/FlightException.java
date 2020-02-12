package com.capgemini.uraan.skybook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FlightException extends RuntimeException {

	private static final long serialVersionUID = -4005267182579342970L;


	public FlightException() {
		
	}

	
	public FlightException(String message) {
		super(message);
		
	}

	
	public FlightException(Throwable cause) {
		super(cause);
		
	}

	
	public FlightException(String message, Throwable cause) {
		super(message, cause);
		
	}

	
	public FlightException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
