package com.springproject.ECommerceSystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.ECommerceSystem.dto.CategoryRequest;
import com.springproject.ECommerceSystem.dto.CategoryResponse;
import com.springproject.ECommerceSystem.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id){
		return ResponseEntity.ok(categoryService.getCategoryById(id));
	}
	@GetMapping()
	public ResponseEntity<List<CategoryResponse>> getAllCategories(){
		return ResponseEntity.ok(categoryService.getAllCategories());
	}

	
	
}
