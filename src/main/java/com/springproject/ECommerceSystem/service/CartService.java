package com.springproject.ECommerceSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.springproject.ECommerceSystem.dto.CartItemRequest;
import com.springproject.ECommerceSystem.dto.CartItemResponse;
import com.springproject.ECommerceSystem.dto.CartResponse;
import com.springproject.ECommerceSystem.entity.Cart;
import com.springproject.ECommerceSystem.entity.CartItems;
import com.springproject.ECommerceSystem.entity.Product;
import com.springproject.ECommerceSystem.entity.User;
import com.springproject.ECommerceSystem.exception.ProductNotFoundException;
import com.springproject.ECommerceSystem.exception.UserNotFoundException;
import com.springproject.ECommerceSystem.repo.CartItemRepository;
import com.springproject.ECommerceSystem.repo.CartRepository;
import com.springproject.ECommerceSystem.repo.ProductRepository;
import com.springproject.ECommerceSystem.repo.UserRepository;

@Service
public class CartService {
	private UserRepository userRepository;
	private ProductRepository productRepository;
	private CartItemRepository cartItemRepository;
	private CartRepository cartRepository;
	public CartService(UserRepository userRepository, ProductRepository productRepository,
			CartItemRepository cartItemRepository, CartRepository cartRepository) {
		super();
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.cartItemRepository = cartItemRepository;
		this.cartRepository = cartRepository;
	}
	private User getCurrentUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		return userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException("User with username: "+username+" does not exist"));
	}
	public CartResponse getUserCart() {
		User user = getCurrentUser();
		Cart cart = cartRepository.findByUser(user)
					.orElseGet(()->{
							Cart newCart = new Cart();
							newCart.setUser(user);
							return cartRepository.save(newCart);
					});
		return mapToCartResponse(cart);
	}
	public void addToCart(CartItemRequest itemRequest) {
		Cart cart = mapToCart(getUserCart());
		Product product = productRepository.findById(itemRequest.getProductId()).orElseThrow(()->new ProductNotFoundException("Product with id: "+itemRequest.getProductId()+" does not exist"));
		CartItems existingItem = null;
		for(CartItems item : cart.getItems()) {
			if(item.getProduct().getId().equals(product.getId())) {
				existingItem = item;
				break;
			}
		}
		if(existingItem!=null) {
			existingItem.setQuantity(existingItem.getQuantity()+itemRequest.getQuantity());
			existingItem.setTotalPrice(existingItem.getTotalPrice()*existingItem.getQuantity());
		}
		else {
			CartItems newItem =new CartItems();
			newItem.setCart(cart);
			newItem.setProduct(product);
			newItem.setQuantity(itemRequest.getQuantity());
			newItem.setTotalPrice(product.getPrice()*itemRequest.getQuantity());
			cart.getItems().add(newItem);
		}
//		double totalAmount=0.0;
//		for(CartItems item: cart.getItems()) {
//			totalAmount+=item.getTotalPrice();
//		}
		cart.setTotalPrice(calculateTotalCartAmount(cart));
		cartRepository.save(cart);
	}
	public void removeItem(Long productId) {
		Cart cart = mapToCart(getUserCart());
		CartItems removableItem =cart.getItems().stream().filter(item -> item.getProduct().getId().equals(productId)).findFirst().orElseThrow(()-> new ProductNotFoundException("Product with id: "+productId+" not exists in the cart "));
		cart.getItems().remove(removableItem);
		cart.setTotalPrice(calculateTotalCartAmount(cart));
		cartRepository.save(cart);
		
	}
	public void updateItemQuantity(Long productId,Integer quantity) {
		Cart cart = mapToCart(getUserCart());
		boolean isPresent = false;
		for(CartItems item:cart.getItems()) {
			if(item.getProduct().getId().equals(productId)) {
				isPresent = true;
				item.setQuantity(quantity);
				item.setTotalPrice(quantity*item.getProduct().getPrice());
				break;
			}
		}
		if(!isPresent) {
			throw new ProductNotFoundException("Product with id: "+productId+" does not exist in the cart");
		}
		calculateTotalCartAmount(cart);
		cartRepository.save(cart);
	}
	public void clearCart() {
		Cart cart = mapToCart(getUserCart());
//		for(CartItems item:cart.getItems()) {
//			cart.getItems().remove(item);
//		}
		cart.getItems().clear();
		cart.setTotalPrice(0.0);
		cartRepository.save(cart);
	}
	
	public Double calculateTotalCartAmount(Cart cart) {
		double totalAmount=0.0;
		for(CartItems item: cart.getItems()) {
			totalAmount+=item.getTotalPrice();
		}
		return totalAmount;
	}
	private CartResponse mapToCartResponse(Cart cart) {
		CartResponse cartResponse = new CartResponse();
		List<CartItemResponse> items = new ArrayList<>();
		for(CartItems item : cart.getItems()) {
			CartItemResponse cartItemResponse = new CartItemResponse();
			cartItemResponse.setId(item.getId());
			cartItemResponse.setProductId(item.getProduct().getId());
			cartItemResponse.setProductName(item.getProduct().getName());
			cartItemResponse.setPrice(item.getTotalPrice());
			cartItemResponse.setQuantity(item.getQuantity());
			items.add(cartItemResponse);
		}
		cartResponse.setId(cart.getId());
		cartResponse.setItems(items);
		cartResponse.setTotalAmount(cart.getTotalPrice());
		return cartResponse;
	}
	private Cart mapToCart(CartResponse cartResponse) {
		Cart cart = new Cart();
		List<CartItems> items = new ArrayList<>();
		for(CartItemResponse cartItemResponse : cartResponse.getItems()) {
			CartItems item = new CartItems();
			Product product = productRepository.findById(cartItemResponse.getProductId()).orElseThrow(()->new ProductNotFoundException("Product with id: "+cartItemResponse.getProductId()+" does not exist"));
			item.setId(cartItemResponse.getId());
			item.setProduct(product);
			item.setCart(cart);
			item.setQuantity(cartItemResponse.getQuantity());
			item.setTotalPrice(cartItemResponse.getPrice());
			items.add(item);
		}
		cart.setItems(items);
		cart.setId(cartResponse.getId());
		cart.setTotalPrice(cartResponse.getTotalAmount());
		cart.setUser(getCurrentUser());
		return cart;
	}
}
