package com.springproject.ECommerceSystem.dto;

public class OrderItemResponse {
	private Long id;

    private int quantity;

    private double totalPrice;
    private Long productId;
    private String productName;
	public OrderItemResponse() {
		super();
	}
	public OrderItemResponse(Long id, int quantity, double totalPrice, Long productId, String productName) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.productId = productId;
		this.productName = productName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
    
}
