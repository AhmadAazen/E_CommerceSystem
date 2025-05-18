package com.springproject.ECommerceSystem.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.ECommerceSystem.entity.Cart;
import com.springproject.ECommerceSystem.entity.User;

public interface CartRepository extends JpaRepository<Cart, Long>{
	Optional<Cart> findByUser(User user);
}
