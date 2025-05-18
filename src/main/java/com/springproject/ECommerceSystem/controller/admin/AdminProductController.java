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

import com.springproject.ECommerceSystem.dto.ProductRequest;
import com.springproject.ECommerceSystem.dto.ProductResponse;
import com.springproject.ECommerceSystem.service.ProductService;

@RestController
@RequestMapping("/api/admin/products")
@PreAuthorize("hasRole('ADMIN')")

public class AdminProductController {
	private ProductService productService;

	public AdminProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	@PostMapping
	public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest){
		return new ResponseEntity<ProductResponse>(productService.addProduct(productRequest),HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest,@PathVariable Long id){
		return new ResponseEntity<ProductResponse>(productService.updateProduct(id,productRequest),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id){
		return new ResponseEntity<>(productService.deleteProductById(id),HttpStatus.OK);
	}
}
