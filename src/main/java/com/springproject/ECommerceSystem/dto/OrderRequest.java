package com.springproject.ECommerceSystem.dto;

public class OrderRequest {
	private Long userId;
	private String paymentMethod;
	public OrderRequest() {
		super();
	}
	public OrderRequest(Long userId, String paymentMethod) {
		super();
		this.userId = userId;
		this.paymentMethod = paymentMethod;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
}
