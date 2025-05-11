package com.springproject.ECommerceSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.ECommerceSystem.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
