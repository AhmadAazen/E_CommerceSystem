package com.springproject.ECommerceSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler(value= UserAlreadyExistsException.class)
	public ResponseEntity<ExceptionInfo> handleUserAlreadyExistsException(UserAlreadyExistsException uaee){
		ExceptionInfo exception = new ExceptionInfo();
		exception.setMessage(uaee.getMessage());
		exception.setCode("123456");
		return new ResponseEntity<ExceptionInfo>(exception,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
