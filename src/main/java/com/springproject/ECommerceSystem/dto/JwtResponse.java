package com.springproject.ECommerceSystem.dto;

import java.util.List;
import java.util.Set;

public class JwtResponse {
	 private String token;
	    private String type = "Bearer";
//	    private Long id;
	    private String username;
	    private Set<String> roles;

	    public JwtResponse(String token, String username, Set<String> roles) {
	        this.token = token;
//	        this.id = id;
	        this.username = username;
	        this.roles = roles;
	    }

	    // Getters and setters
	    public String getToken() { 
	    	return token; 
	    	}
	    public void setToken(String token) { 
	    	this.token = token; 
	    	}

	    public String getType() { 
	    	return type; 
	    	}
	    public void setType(String type) {
	    	this.type = type; 
	    	}

//	    public Long getId() {
//	    	return id; 
//	    	}
//	    public void setId(Long id) { 
//	    	this.id = id; 
//	    	}

	    public String getUsername() {
	    	return username; 
	    	}
	    public void setUsername(String username) {
	    	this.username = username; 
	    	}

	    public Set<String> getRoles() { 
	    	return roles; 
	    	}
	    public void setRoles(Set<String> roles) {
	    	this.roles = roles; 
	    	}
}
