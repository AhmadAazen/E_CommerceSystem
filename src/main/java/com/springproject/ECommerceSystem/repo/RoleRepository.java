package com.springproject.ECommerceSystem.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.ECommerceSystem.entity.ERole;
import com.springproject.ECommerceSystem.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
