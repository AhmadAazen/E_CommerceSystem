package com.springproject.ECommerceSystem.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.springproject.ECommerceSystem.dto.OrderItemResponse;
import com.springproject.ECommerceSystem.dto.OrderResponse;
import com.springproject.ECommerceSystem.dto.OrderStatusUpdateRequest;
import com.springproject.ECommerceSystem.entity.Cart;
import com.springproject.ECommerceSystem.entity.CartItems;
import com.springproject.ECommerceSystem.entity.Order;
import com.springproject.ECommerceSystem.entity.OrderItem;
import com.springproject.ECommerceSystem.entity.OrderStatus;
import com.springproject.ECommerceSystem.entity.User;
import com.springproject.ECommerceSystem.exception.CartIsEmptyException;
import com.springproject.ECommerceSystem.exception.OrderNotFoundException;
import com.springproject.ECommerceSystem.exception.UserNotFoundException;
import com.springproject.ECommerceSystem.repo.CartItemRepository;
import com.springproject.ECommerceSystem.repo.CartRepository;
import com.springproject.ECommerceSystem.repo.OrderItemRepository;
import com.springproject.ECommerceSystem.repo.OrderRepository;
import com.springproject.ECommerceSystem.repo.ProductRepository;
import com.springproject.ECommerceSystem.repo.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
	private UserRepository userRepository;
	private ProductRepository productRepository;
	private CartRepository cartRepository;
	private CartItemRepository cartItemRepository;
	private OrderRepository orderRepository;
	private OrderItemRepository itemRepository;
	private CartService cartService;
	public OrderService(UserRepository userRepository, ProductRepository productRepository,
			CartRepository cartRepository, CartItemRepository cartItemRepository, OrderRepository orderRepository,
			OrderItemRepository itemRepository,CartService cartService) {
		super();
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
		this.orderRepository = orderRepository;
		this.itemRepository = itemRepository;
		this.cartService=cartService;
	}
	@Transactional
	public OrderResponse placeOrder(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User with id: "+ userId+" does not exist"));
		Cart cart = cartRepository.findByUser(user)
				.orElseGet(()->{
					Cart newCart = new Cart();
					newCart.setUser(user);
					return cartRepository.save(newCart);
			});
		
//		List<CartItems> cartItems=cart.getItems();
		if(cart.getItems().isEmpty()) {
			throw new CartIsEmptyException("Please add items to the cart and then place the order");
		}
		Order order = new Order();
		List<OrderItem> orderItems = new ArrayList<>();
		for(CartItems cartItem :cart.getItems()) {
			OrderItem orderItem = new OrderItem();
        	

			orderItem.setOrder(order);
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.getProduct().setQuantity(orderItem.getProduct().getQuantity()-orderItem.getQuantity());
			orderItem.setTotalPrice(cartItem.getTotalPrice());
			orderItems.add(orderItem);
		}
		order.setOrderItems(orderItems);
		order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.PROCESSING);
        order.setTotalAmount(cartService.calculateTotalCartAmount(cart));
        order.setUser(user);
        orderRepository.save(order);
        
        cartService.clearCart();
        return mapToOrderResponse(order);
        
	}
	public OrderResponse updateOrderStatus(Long orderId,OrderStatusUpdateRequest request) {
		Order order=orderRepository.findById(orderId).orElseThrow(()->new OrderNotFoundException("Order with id: "+orderId+" does not exist"));
		order.setOrderStatus(request.getOrderStatus());
		orderRepository.save(order);
		return mapToOrderResponse(order);
	}
	public List<Order> getAllOrders(){
		return orderRepository.findAll();
	}
	public List<OrderResponse> getOrdersByUser(Long userId){
		List<Order> orders=orderRepository.findByUserId(userId).orElseThrow(()->new OrderNotFoundException("You have not placed any order yet."));
		List<OrderResponse> orderResponses =new ArrayList<>(); 
		for(Order order:orders) {
			OrderResponse response = mapToOrderResponse(order);
			orderResponses.add(response);
		}
		return orderResponses;
	}
	private OrderResponse mapToOrderResponse(Order order) {
		OrderResponse orderResponse = new OrderResponse();
		List<OrderItemResponse> orderItems = new ArrayList<>();
		for(OrderItem item:order.getOrderItems()) {
			OrderItemResponse orderItemResponse = new OrderItemResponse();
			orderItemResponse.setId(item.getId());
			orderItemResponse.setProductId(item.getProduct().getId());
			orderItemResponse.setProductName(item.getProduct().getName());
			orderItemResponse.setQuantity(item.getQuantity());
			orderItemResponse.setTotalPrice(item.getTotalPrice());
			orderItems.add(orderItemResponse);
		}
		orderResponse.setItems(orderItems);
		orderResponse.setId(order.getId());
		orderResponse.setOrderDate(order.getOrderDate());
		orderResponse.setOrderNumber(order.getOrderNumber());
		orderResponse.setOrderStatus(order.getOrderStatus());
		orderResponse.setTotalAmount(order.getTotalAmount());
		orderResponse.setUserId(order.getUser().getId());
		return orderResponse;
	}
}
