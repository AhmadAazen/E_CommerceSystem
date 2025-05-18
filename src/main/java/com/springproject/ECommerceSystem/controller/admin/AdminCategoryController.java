package com.springproject.ECommerceSystem.controller.admin;

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
@RequestMapping("/api/admin/categories")
@PreAuthorize("hasRole('ADMIN')")

public class AdminCategoryController {
	private CategoryService categoryService;

	public AdminCategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	
	@PostMapping
	public ResponseEntity<CategoryResponse> addCategory(@RequestBody CategoryRequest categoryRequest){
		return new ResponseEntity<>(categoryService.addCategory(categoryRequest),HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<CategoryResponse> updateCategory(@RequestBody CategoryRequest categoryRequest, @PathVariable Long id){
		return new ResponseEntity<>(categoryService.updateCategory(id,categoryRequest),HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategoryById(@PathVariable Long id){
		return ResponseEntity.ok(categoryService.deleteCategoryById(id));
	}
	
	
}
