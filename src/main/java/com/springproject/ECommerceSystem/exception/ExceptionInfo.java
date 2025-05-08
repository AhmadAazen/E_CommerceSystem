package com.springproject.ECommerceSystem.exception;

import lombok.Data;

@Data
public class ExceptionInfo {
	private String message;
	private String code;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
