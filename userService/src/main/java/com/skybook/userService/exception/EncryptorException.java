package com.skybook.userService.exception;

public class EncryptorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4439515241187174926L;

	public EncryptorException() {
		
		super();
	}

	public EncryptorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public EncryptorException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public EncryptorException(String message) {
		super(message);
		
	}

	public EncryptorException(Throwable cause) {
		super(cause);
		
	}
	
	

}
