package com.springproject.ECommerceSystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.ECommerceSystem.dto.CartItemRequest;
import com.springproject.ECommerceSystem.dto.CartResponse;
import com.springproject.ECommerceSystem.service.CartService;

@RestController
@RequestMapping("/api/carts")
@PreAuthorize("hasRole('USER')")
public class CartController {
	private CartService cartService;
	public CartController(CartService cartService) {
		// TODO Auto-generated constructor stub
		super();
		this.cartService=cartService;
	}
	@GetMapping
	public ResponseEntity<CartResponse> getUserCart(){
		return ResponseEntity.ok(cartService.getUserCart());
	}
	@PostMapping
	public ResponseEntity<Object> addToCart(@RequestBody CartItemRequest cartItemRequest){
		cartService.addToCart(cartItemRequest);
		return ResponseEntity.ok().build();
	}
	@DeleteMapping("/{productId}")
	public ResponseEntity<Object> removeItem(@PathVariable Long productId){
		cartService.removeItem(productId);
		return ResponseEntity.ok().build();

	}
	@PutMapping
	public ResponseEntity<Object> updateItemQuantity(@RequestBody CartItemRequest request){
		cartService.updateItemQuantity(request.getProductId(),request.getQuantity());
		return ResponseEntity.ok().build();
	}
	@DeleteMapping
	public ResponseEntity<Object> clearCart(){
		cartService.clearCart();;
		return ResponseEntity.ok().build();
	}
}
