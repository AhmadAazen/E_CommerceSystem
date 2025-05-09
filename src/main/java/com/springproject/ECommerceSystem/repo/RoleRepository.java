package com.springproject.ECommerceSystem.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springproject.ECommerceSystem.entity.ERole;
import com.springproject.ECommerceSystem.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
		
		Optional<Role> findByName(ERole name);

	    @Query("SELECT r FROM Role r WHERE r.name = :name")
	    Optional<Role> findByName(@Param("name") String name);
}
