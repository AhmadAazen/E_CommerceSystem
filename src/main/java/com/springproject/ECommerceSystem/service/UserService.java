package com.springproject.ECommerceSystem.service;

import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springproject.ECommerceSystem.entity.Role;
import com.springproject.ECommerceSystem.entity.User;
import com.springproject.ECommerceSystem.exception.UserAlreadyExistsException;
import com.springproject.ECommerceSystem.repo.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;	
	
	private BCryptPasswordEncoder pwdEncoder;
	public UserService(UserRepository userRepository, BCryptPasswordEncoder pwdEncoder) {
        this.userRepository = userRepository;
        this.pwdEncoder = pwdEncoder;
    }
//	public Set<Role> getRoles(Set<ERole> roleNames) {
//	    Map<ERole, Role> roleMap = roleRepository.findAll().stream()
//	        .collect(Collectors.toMap(Role::getName, Function.identity()));
//
//	    return roleNames.stream()
//	        .map(name -> Optional.ofNullable(roleMap.get(name))
//	            .orElseThrow(() -> new RuntimeException("Role not found: " + name)))
//	        .collect(Collectors.toSet());
//	}

	public User registerUser(String username, String pwd, Set<Role> roleNames) {
		if(userRepository.findByUsername(username).isPresent()) {
			throw new UserAlreadyExistsException("Username already exists");
		}
		User user = new User();
		user.setUsername(username);
		
//		Set<Role> roles = roleNames.stream()
//			    .map(name -> roleRepository.findByName(ERole.valueOf(name)) // Ensures the name is valid as an enum
//			        .orElseThrow(() -> new RuntimeException("Role not found: " + name))) // Handles the missing role case
//			    .collect(Collectors.toSet());
		user.setRole(roleNames);
		user.setPwd(pwdEncoder.encode(pwd));
		return userRepository.save(user);
	}
}
