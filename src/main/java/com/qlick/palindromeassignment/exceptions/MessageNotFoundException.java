package com.qlick.palindromeassignment.exceptions;

public class MessageNotFoundException extends RuntimeException{

	public MessageNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public MessageNotFoundException(String message) {
		super(message);
	}

	public MessageNotFoundException(Throwable cause) {
		super(cause);
	}
	
	

}
