package com.sg.ems.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4116378116282506796L;

	public EntityNotFoundException(String message) {
		super(message);
	}

}
