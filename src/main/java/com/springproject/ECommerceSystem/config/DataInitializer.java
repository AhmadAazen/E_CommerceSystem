package com.springproject.ECommerceSystem.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.springproject.ECommerceSystem.entity.ERole;
import com.springproject.ECommerceSystem.entity.Role;
import com.springproject.ECommerceSystem.repo.RoleRepository;

@Component
public class DataInitializer implements CommandLineRunner {
	
	private final RoleRepository roleRepository;
	   public DataInitializer(RoleRepository roleRepository) {
	        this.roleRepository = roleRepository;
	    }
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		 for (ERole role : ERole.values()) {
	            roleRepository.findByName(role).orElseGet(() -> {
	                Role newRole = new Role(role);
	                return roleRepository.save(newRole);
	            });
		
	}

	}
	}
