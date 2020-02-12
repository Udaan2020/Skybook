package com.skybook.userService.exception;

public class UserException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7149526762884447141L;

	
	/**
	 * 
	 */
	public UserException() {
		super();

	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	/**
	 * @param message
	 * @param cause
	 */
	public UserException(String message, Throwable cause) {
		super(message, cause);

	}

	/**
	 * @param message
	 */
	public UserException(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public UserException(Throwable cause) {
		super(cause);

	}
}
