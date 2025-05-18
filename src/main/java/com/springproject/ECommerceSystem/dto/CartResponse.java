package com.springproject.ECommerceSystem.dto;

import java.util.List;

public class CartResponse {
	private Long id;
	private List<CartItemResponse> items;
	private Double totalAmount;
	public CartResponse() {
	}
	public CartResponse(Long id,List<CartItemResponse> items, Double totalAmount) {
		super();
		this.id=id;
		this.items = items;
		this.totalAmount = totalAmount;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<CartItemResponse> getItems() {
		return items;
	}
	public void setItems(List<CartItemResponse> items) {
		this.items = items;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
}
