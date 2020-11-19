package com.qlick.palindromeassignment.exceptions;

import java.util.Date;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MessageRestExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	//method argument not valid exception
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		
		//create a message error response
		MessageErrorResponse  error = new MessageErrorResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		
		error.setMessage("Invalid Arguments");
		
		error.setTimeStamp(new Date());

		//return response entity
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	
	//add Exception handling code
	@ExceptionHandler
	public ResponseEntity<MessageErrorResponse> handleException(MessageNotFoundException ex)
	{
		//create a message error response
		MessageErrorResponse  error = new MessageErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		
		error.setMessage(ex.getMessage());
		
		error.setTimeStamp(new Date());
		
		//return response entity
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		
	}
	
	
	//invalid http method
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		//create a message error response
		MessageErrorResponse  error = new MessageErrorResponse();
		
		error.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
		
		error.setMessage("Invalid Http Method");
		
		error.setTimeStamp(new Date());
		
		//return response entity
		return new ResponseEntity<>(error,HttpStatus.METHOD_NOT_ALLOWED);
	}
	
//	//generic exception
//	@ExceptionHandler(Exception.class)
//	public final ResponseEntity<Object> handleException(Exception ex, WebRequest request) throws Exception {
//		
//		MessageErrorResponse  error = new MessageErrorResponse();
//		
//		error.setStatus();
//		
//		error.setMessage(ex.getMessage());
//		
//		error.setTimeStamp(new Date());
//
//		
//		
//	}
	
	
}
