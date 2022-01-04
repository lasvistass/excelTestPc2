package com.netgroup.exceldemo.exception;

public class UserNotLoggedException extends Exception {
	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotLoggedException(String errorMessage){
	        super(errorMessage);
	    }
}
