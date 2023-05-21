package com.techway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techway.dto.request.OrderRequest;
import com.techway.dto.response.OrderResponse;
import com.techway.service.OrderService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/orders")
public class OrderController {
	
	//1. lấy ra danh sách đơn hàng theo user(Role)
	//2. 
	
	@Autowired
	private OrderService orderService;

	//lay danh sach order
	@GetMapping()
	public ResponseEntity<List<OrderResponse>> getAllOrders(Authentication authentication) {
		String email = authentication.getName();
		List<OrderResponse> orders = orderService.getAllOrdersByEmail(email);
		if (orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	//get an order
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponse> getOrderById(@PathVariable String orderId, Authentication authn) {
		String email = authn.getName();
		OrderResponse order = orderService.getOrderById(orderId, email);
		if (order == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	// place order
	@PostMapping()
	public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest, Authentication authn) {
		String email = authn.getName();
		OrderResponse order = orderService.placeOrder(orderRequest, email);
		return new ResponseEntity<>(order, HttpStatus.CREATED);
	}
	

	@DeleteMapping("/{orderId}")
	public ResponseEntity<HttpStatus> deleteOrder(@PathVariable String orderId, Authentication authn) {
		String email = authn.getName();
		boolean result = orderService.deleteOrder(orderId, email);
		if (!result) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
