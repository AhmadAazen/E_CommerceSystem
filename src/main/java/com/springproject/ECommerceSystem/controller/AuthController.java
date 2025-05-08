package com.springproject.ECommerceSystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.ECommerceSystem.entity.User;
import com.springproject.ECommerceSystem.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private UserService userService;

	public AuthController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user){
		userService.registerUser(user.getUsername(), user.getPwd(), user.getRole());
		return new ResponseEntity<String>("UserEnteredSuccessfully",HttpStatus.CREATED);
	}
}
