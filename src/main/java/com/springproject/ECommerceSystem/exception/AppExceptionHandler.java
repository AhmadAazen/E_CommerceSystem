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
		exception.setCode("400");
		return new ResponseEntity<ExceptionInfo>(exception,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value= CategoryNotFoundException.class)
	public ResponseEntity<ExceptionInfo> handleCategoryNotFoundException(CategoryNotFoundException cnfe){
		ExceptionInfo exception = new ExceptionInfo();
		exception.setMessage(cnfe.getMessage());
		exception.setCode("404");
		return new ResponseEntity<ExceptionInfo>(exception,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value= ProductAlreadyExistsException.class)
	public ResponseEntity<ExceptionInfo> handleProductAlreadyExistsException(ProductAlreadyExistsException paee){
		ExceptionInfo exception = new ExceptionInfo();
		exception.setMessage(paee.getMessage());
		exception.setCode("400");
		return new ResponseEntity<ExceptionInfo>(exception,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value= ProductNotFoundException.class)
	public ResponseEntity<ExceptionInfo> handleProductNotFoundException(ProductNotFoundException pnfe){
		ExceptionInfo exception = new ExceptionInfo();
		exception.setMessage(pnfe.getMessage());
		exception.setCode("404");
		return new ResponseEntity<ExceptionInfo>(exception,HttpStatus.NOT_FOUND);
	}
}
