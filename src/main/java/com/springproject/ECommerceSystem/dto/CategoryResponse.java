package com.springproject.ECommerceSystem.dto;

public class CategoryResponse {
	private Long id;
	private String name;
	private String description;
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
	public void setDesciption(String descrription) {
		this.description = description;
	}
	public CategoryResponse(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public CategoryResponse() {
	}
	
	
}
