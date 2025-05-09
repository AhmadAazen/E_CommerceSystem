package com.springproject.ECommerceSystem.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springproject.ECommerceSystem.entity.ERole;
import com.springproject.ECommerceSystem.entity.Role;
import com.springproject.ECommerceSystem.entity.User;
import com.springproject.ECommerceSystem.exception.UserAlreadyExistsException;
import com.springproject.ECommerceSystem.repo.RoleRepository;
import com.springproject.ECommerceSystem.repo.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;	
	
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder pwdEncoder;
	public UserService(UserRepository userRepository, BCryptPasswordEncoder pwdEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.pwdEncoder = pwdEncoder;
        this.roleRepository=roleRepository;
    }


	public User registerUser(String username, String pwd, Set<String> roleNames) {
		if(userRepository.findByUsername(username).isPresent()) {
			throw new UserAlreadyExistsException("Username already exists");
		}
		Set<Role> roles = roleNames.stream()
			    .map(roleName -> {
			        ERole enumRole = ERole.valueOf(roleName); // Converts string to enum
			        return roleRepository.findByName(enumRole)
			            .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
			    })
			    .collect(Collectors.toSet());
		User user = new User();
		user.setUsername(username);
		

		user.setRole(roles);
		user.setPwd(pwdEncoder.encode(pwd));
		return userRepository.save(user);
	}
}
