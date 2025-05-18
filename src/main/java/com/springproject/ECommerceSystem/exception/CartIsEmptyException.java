package com.springproject.ECommerceSystem.exception;

public class CartIsEmptyException extends RuntimeException {
	public CartIsEmptyException(String msg) {
		super(msg);
	}
}
