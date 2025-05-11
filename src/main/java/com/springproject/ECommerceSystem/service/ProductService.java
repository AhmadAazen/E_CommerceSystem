package com.springproject.ECommerceSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springproject.ECommerceSystem.dto.CategoryResponse;
import com.springproject.ECommerceSystem.dto.ProductRequest;
import com.springproject.ECommerceSystem.dto.ProductResponse;
import com.springproject.ECommerceSystem.entity.Category;
import com.springproject.ECommerceSystem.entity.Product;
import com.springproject.ECommerceSystem.exception.CategoryNotFoundException;
import com.springproject.ECommerceSystem.exception.ProductAlreadyExistsException;
import com.springproject.ECommerceSystem.exception.ProductNotFoundException;
import com.springproject.ECommerceSystem.repo.CategoryRepository;
import com.springproject.ECommerceSystem.repo.ProductRepository;

@Service
public class ProductService {
	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	public ProductService(ProductRepository productRepository,CategoryRepository categoryRepository) {
		super();
		this.productRepository = productRepository;
		this.categoryRepository=categoryRepository;
	}
	public ProductResponse addProduct(ProductRequest productRequest) {
		 if(productRepository.findByName(productRequest.getName()).isPresent()) {
			 throw new ProductAlreadyExistsException(productRequest.getName()+"alreadyexists");
		 }
		 Category category = categoryRepository.findById(productRequest.getCategoryId())
				 							   .orElseThrow(() -> new CategoryNotFoundException("Category with id: "+productRequest.getCategoryId()+" does not exist"));
		 CategoryResponse categoryResponse = new CategoryResponse(category.getId(), category.getName(), category.getDescription());

		 Product product = new Product();
		 product.setName(productRequest.getName());
		 product.setDescription(productRequest.getDescription());
		 product.setPrice(productRequest.getPrice());
		 product.setQuantity(productRequest.getQuantity());
		 product.setCategory(category);
		 Product saved = productRepository.save(product);
		 return new ProductResponse(saved.getId(), saved.getName(), saved.getDescription(), saved.getPrice(), saved.getQuantity(), categoryResponse);
		 
	}
	public ProductResponse updateProduct(Long id,ProductRequest productRequest) {
		Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product with id: "+id+" does not exist"));
		if(productRequest.getName()!=null) {
			product.setName(productRequest.getName());
		}
		if(productRequest.getDescription()!=null) {
			product.setDescription(productRequest.getDescription());
		}
		if(productRequest.getPrice()!=null) {
			product.setPrice(productRequest.getPrice());
		}
		if(productRequest.getQuantity()!=null) {
			product.setQuantity(productRequest.getQuantity());
		}
		if(productRequest.getCategoryId()!=null) {
			Category updatedCategory = categoryRepository.findById(productRequest.getCategoryId())
												  .orElseThrow(() -> new CategoryNotFoundException("Category with id: "+productRequest.getCategoryId()+" does not exist"));
			product.setCategory(updatedCategory);
		}
		Product updated = productRepository.save(product);
		Category updatedCategory=updated.getCategory();
		CategoryResponse categoryResponse = new CategoryResponse(updatedCategory.getId(),updatedCategory.getName(),updatedCategory.getDescription());
		return new ProductResponse(updated.getId(), updated.getName(), updated.getDescription(), updated.getPrice(), updated.getQuantity(), categoryResponse);
	}
	public List<ProductResponse> getAllProducts(){
		List<Product> products = productRepository.findAll();
		List<ProductResponse> productResponse = new ArrayList<>();
		for(Product product:products) {
			Category category = product.getCategory();
			CategoryResponse categoryResponse = new CategoryResponse(category.getId(), category.getName(), category.getDescription());
			ProductResponse response = new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), categoryResponse);
			productResponse.add(response);
		}
		return productResponse;
	}
	public ProductResponse getProductsById(Long id) {
		Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product with id: "+id+" does not exist"));
		Category category = product.getCategory();
		CategoryResponse categoryResponse = new CategoryResponse(category.getId(), category.getName(), category.getDescription());
		return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), categoryResponse);
	}
	public String deleteProductById(Long id) {
		if(productRepository.existsById(id)) {
			productRepository.deleteById(id);
			return "Product Deleted Successfully";
		}
		return "Product with id: "+id+" does not exist";
	}
}
