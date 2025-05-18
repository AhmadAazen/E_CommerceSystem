package com.springproject.ECommerceSystem.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.springproject.ECommerceSystem.entity.OrderStatus;

public class OrderResponse {
	private Long id;
	private String orderNumber; // UUID or sequential
    private LocalDateTime orderDate;
	private OrderStatus orderStatus; // PENDING, SHIPPED, DELIVERED, CANCELLED
	private Double totalAmount;
	private Long userId;
	private List<OrderItemResponse> items = new ArrayList<>();
	public OrderResponse() {
		super();
	}
	public OrderResponse(Long id, String orderNumber, LocalDateTime orderDate, OrderStatus orderStatus, Double totalAmount,
			Long userId, List<OrderItemResponse> items) {
		super();
		this.id = id;
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.totalAmount = totalAmount;
		this.userId = userId;
		this.items = items;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<OrderItemResponse> getItems() {
		return items;
	}
	public void setItems(List<OrderItemResponse> items) {
		this.items = items;
	}
	
	
	
}
