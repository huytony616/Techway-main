package com.techway.service;

import java.util.List;

import com.techway.dto.request.OrderRequest;
import com.techway.dto.response.OrderResponse;

public interface OrderService {
	
	List<OrderResponse> getAllOrdersByEmail(String email);

	OrderResponse getOrderById(String id, String email);

	OrderResponse placeOrder(OrderRequest orderRequest, String email);

	boolean deleteOrder(String id, String email);

}
