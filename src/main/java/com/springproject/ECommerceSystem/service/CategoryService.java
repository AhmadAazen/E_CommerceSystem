package com.springproject.ECommerceSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springproject.ECommerceSystem.dto.CategoryRequest;
import com.springproject.ECommerceSystem.dto.CategoryResponse;
import com.springproject.ECommerceSystem.entity.Category;
import com.springproject.ECommerceSystem.exception.CategoryNotFoundException;
import com.springproject.ECommerceSystem.repo.CategoryRepository;
@Service
public class CategoryService {
	private CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}
	
	public CategoryResponse getCategoryById(Long id) {
		Category c= categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category with id: "+id+" does not exist"));
		return new CategoryResponse(c.getId(),c.getName(),c.getDescription());
	}
	public List<CategoryResponse> getAllCategories(){
		List<Category> categories =categoryRepository.findAll();
		List<CategoryResponse> categoryResponse = new ArrayList<>();
		for(Category category:categories) {
			CategoryResponse response = new CategoryResponse(category.getId(), category.getName(), category.getDescription());
			categoryResponse.add(response);
		}
		return categoryResponse;
	}
	public CategoryResponse addCategory(CategoryRequest categoryRequest) {
		Category category = new Category();
		category.setName(categoryRequest.getName());
		category.setDescription(categoryRequest.getDescription());
		Category saved =categoryRepository.save(category);
		return new CategoryResponse(saved.getId(), saved.getName(), saved.getDescription());
	}
	public CategoryResponse updateCategory(Long id,CategoryRequest categoryRequest) {
		Category category= categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category with id: "+id+" does not exist"));
		if(categoryRequest.getName()!=null) {
			category.setName(categoryRequest.getName());
		}
		if(categoryRequest.getDescription()!=null) {
			category.setDescription(categoryRequest.getDescription());
		}
		Category updated = categoryRepository.save(category);
		return new CategoryResponse(updated.getId(), updated.getName(), updated.getDescription());
	}
	public String deleteCategoryById(Long id) {
		if(categoryRepository.findById(id).isPresent()) {
		 categoryRepository.deleteById(id);
		 return "Category deleted Successfully";
		 }
		return "Category with the entered id does not exist";
		 
	}
}
