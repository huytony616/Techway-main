package com.techway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String message;	
	public ResourceNotFoundException(String message) {
		super(message);
		this.message = message;
	}
	
}
