package com.springproject.ECommerceSystem.config.security.service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springproject.ECommerceSystem.entity.User;

public class UserDetailsImpl implements UserDetails{

    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

	
	 public UserDetailsImpl(Long id, String username, String password,
             Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	 }
	 public static UserDetailsImpl build(User user) {
	        Set<GrantedAuthority> authorities = user.getRole().stream()
	                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
	                .collect(Collectors.toSet());

	        return new UserDetailsImpl(
	                user.getId(),
	                user.getUsername(),
	                user.getPwd(),
	                authorities);
	    }
	 @Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			return authorities;
		}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

}
