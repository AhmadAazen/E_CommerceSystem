package com.springproject.ECommerceSystem.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.ECommerceSystem.config.security.JwtUtils;
import com.springproject.ECommerceSystem.config.security.service.UserDetailsImpl;
import com.springproject.ECommerceSystem.dto.JwtResponse;
import com.springproject.ECommerceSystem.dto.LoginRequest;
import com.springproject.ECommerceSystem.dto.SignupRequest;
import com.springproject.ECommerceSystem.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private UserService userService;
	private AuthenticationManager authenticationManager;
	private JwtUtils jwtUtils;
	public AuthController(UserService userService,AuthenticationManager authenticationManager,JwtUtils jwtUtils) {
		super();
		this.userService = userService;
		this.authenticationManager=authenticationManager;
		this.jwtUtils=jwtUtils;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody SignupRequest signupRequest){
		userService.registerUser(
				signupRequest.getUsername(),
				signupRequest.getPwd(),
				signupRequest.getRole()
		);
		return new ResponseEntity<String>("UserEnteredSuccessfully",HttpStatus.CREATED);
	}
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
		org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPwd())
				);
		 SecurityContextHolder.getContext().setAuthentication(authentication);
		    String jwt = jwtUtils.generateJwtToken((UserDetails) authentication.getPrincipal());

		    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		    Set<String> roles = userDetails.getAuthorities().stream()
		            .map(GrantedAuthority::getAuthority)
		            .collect(Collectors.toSet());

		    return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), roles));
				
	}
	@GetMapping("/welcome")
	public String getWelcMsg() {
		return "Welcome to my ecommerce website";
	}
}
