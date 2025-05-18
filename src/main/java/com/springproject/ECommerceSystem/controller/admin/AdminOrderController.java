package com.springproject.ECommerceSystem.controller.admin;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.ECommerceSystem.dto.OrderResponse;
import com.springproject.ECommerceSystem.dto.OrderStatusUpdateRequest;
import com.springproject.ECommerceSystem.entity.Order;
import com.springproject.ECommerceSystem.repo.UserRepository;
import com.springproject.ECommerceSystem.service.OrderService;

@RestController
@RequestMapping("/api/admin/orders")
@PreAuthorize("hasRole('ADMIN')")
public class AdminOrderController {
	private OrderService orderService;
	private UserRepository userRepository;
	public AdminOrderController(OrderService orderService, UserRepository userRepository) {
		super();
		this.orderService = orderService;
		this.userRepository = userRepository;
	}
	@PutMapping("/status/{orderId}")
	public ResponseEntity<OrderResponse> updateOrderStatus(@PathVariable Long orderId,@RequestBody OrderStatusUpdateRequest request){
		return ResponseEntity.ok(orderService.updateOrderStatus(orderId, request));
	}
	@GetMapping
	public ResponseEntity<List<Order>> getAllOrders(){
		return ResponseEntity.ok(orderService.getAllOrders());
	}
}
