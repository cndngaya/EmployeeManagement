package com.sg.ems.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request) throws Exception {
		
		ExceptionResponse exceptionResponse =new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	@ExceptionHandler(EntityNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleAllException(EntityNotFoundException ex, WebRequest request) throws Exception {
		
		ExceptionResponse exceptionResponse =new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exceptionResponse,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(IdNullException.class)
	public final ResponseEntity<ExceptionResponse> handleIdNullException(IdNullException ex, WebRequest request) throws Exception {
		
		ExceptionResponse exceptionResponse =new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exceptionResponse,HttpStatus.NO_CONTENT);
	}
	
	
	@ExceptionHandler(EntityNullException.class)
	public final ResponseEntity<ExceptionResponse> handleEntityNullException(EntityNullException ex, WebRequest request) throws Exception {
		
		ExceptionResponse exceptionResponse =new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
}
