package com.springproject.ECommerceSystem.dto;

import com.springproject.ECommerceSystem.entity.OrderStatus;

public class OrderStatusUpdateRequest {
    private OrderStatus orderStatus;

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
    
}