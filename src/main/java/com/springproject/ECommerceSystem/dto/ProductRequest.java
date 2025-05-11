package com.springproject.ECommerceSystem.dto;

public class ProductRequest {
	private String name;
	private String description;
	private Double price;
	private Integer quantity;
	private Long categoryId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public ProductRequest() {
	}
	public ProductRequest(String name, String description, Double price, Integer quantity, Long categoryId) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.categoryId = categoryId;
	}
	
	
}
