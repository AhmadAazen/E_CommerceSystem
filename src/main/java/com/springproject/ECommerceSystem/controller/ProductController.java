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

import com.springproject.ECommerceSystem.dto.ProductRequest;
import com.springproject.ECommerceSystem.dto.ProductResponse;
import com.springproject.ECommerceSystem.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	@GetMapping
	public ResponseEntity<List<ProductResponse>> getAllProducts(){
		return new ResponseEntity<List<ProductResponse>>(productService.getAllProducts(),HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id){
		return new ResponseEntity<ProductResponse>(productService.getProductsById(id),HttpStatus.OK);
	}
	
}
