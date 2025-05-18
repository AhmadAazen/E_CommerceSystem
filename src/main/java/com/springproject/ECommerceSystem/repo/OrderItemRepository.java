package com.springproject.ECommerceSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.ECommerceSystem.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
