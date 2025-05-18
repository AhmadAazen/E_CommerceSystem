package com.springproject.ECommerceSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.ECommerceSystem.entity.CartItems;

public interface CartItemRepository extends JpaRepository<CartItems, Long>{

}
