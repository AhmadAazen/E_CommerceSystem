package com.springproject.ECommerceSystem.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.ECommerceSystem.dto.OrderResponse;
import com.springproject.ECommerceSystem.entity.User;
import com.springproject.ECommerceSystem.exception.UserNotFoundException;
import com.springproject.ECommerceSystem.repo.UserRepository;
import com.springproject.ECommerceSystem.service.OrderService;

@RestController
@RequestMapping("/api/orders")
@PreAuthorize("hasRole('USER')")
public class OrderController {
	private OrderService orderService;
	private UserRepository userRepository;
	public OrderController(OrderService orderService, UserRepository userRepository) {
		super();
		this.orderService = orderService;
		this.userRepository = userRepository;
	}
	@PostMapping
	public ResponseEntity<OrderResponse> placeOrder(Principal principal){
		String username = principal.getName();
		User user=userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException("User with username: "+username+" does not exist"));
		return new ResponseEntity<>(orderService.placeOrder(user.getId()),HttpStatus.CREATED);
	}
	@GetMapping
	public ResponseEntity<List<OrderResponse>> getOrdersByUser(Principal principal){
		String username = principal.getName();
		User user=userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException("User with username: "+username+" does not exist"));
		return new ResponseEntity<>(orderService.getOrdersByUser(user.getId()),HttpStatus.OK);
	}
	@PutMapping("/cancel/{orderId}")
	public ResponseEntity<String> cancelOrderAsUser(@PathVariable Long orderId,Principal principal){
		String username = principal.getName();
		User user = userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException("User with username: "+username+" does not exist"));
		
		orderService.cancelOrder(user.getId(), orderId, false);
		return ResponseEntity.ok("Order cancelled successfully by user");
	}
	
}
