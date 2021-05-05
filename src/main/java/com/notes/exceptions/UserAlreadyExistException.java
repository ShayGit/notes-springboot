package com.notes.exceptions;


public class UserAlreadyExistException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistException(final String msg) {
        super(msg);
    }

}
