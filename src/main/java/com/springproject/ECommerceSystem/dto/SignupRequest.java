package com.springproject.ECommerceSystem.dto;

import java.util.Set;

public class SignupRequest {
	
	private String username;
	private String pwd;
	private Set<String> role;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Set<String> getRole() {
		return role;
	}
	public void setRole(Set<String> role) {
		this.role = role;
	}
	
}
