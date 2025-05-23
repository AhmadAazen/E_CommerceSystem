package com.springproject.ECommerceSystem.dto;

public class ProductResponse {
	private Long id;
	private String name;
	private String description;
	private Double price;
	private Integer quantity;
	private CategoryResponse categoryResponse;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public CategoryResponse getCategoryResponse() {
		return categoryResponse;
	}
	public void setCategoryResponse(CategoryResponse categoryResponse) {
		this.categoryResponse = categoryResponse;
	}
	public ProductResponse(Long id, String name, String description, Double price, Integer quantity,
			CategoryResponse categoryResponse) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.categoryResponse = categoryResponse;
	}
	public ProductResponse() {
	}
	
}
