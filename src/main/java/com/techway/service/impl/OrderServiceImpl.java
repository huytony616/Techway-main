package com.techway.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techway.RoleName;
import com.techway.ShippingStatus;
import com.techway.dto.CartItemDto;
import com.techway.dto.OrderDetailDto;
import com.techway.dto.request.OrderRequest;
import com.techway.dto.response.OrderResponse;
import com.techway.entity.Order;
import com.techway.entity.OrderDetail;
import com.techway.entity.Role;
import com.techway.entity.User;
import com.techway.exception.APIException;
import com.techway.exception.ResourceNotFoundException;
import com.techway.repository.CartItemRepository;
import com.techway.repository.OrderDetailRepository;
import com.techway.repository.OrderRepository;
import com.techway.repository.ProductRepository;
import com.techway.repository.RoleRepository;
import com.techway.repository.UserRepository;
import com.techway.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CartItemRepository cartItemRepository; 
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	//1. lấy danh sách order theo user email
	//2. lấy order theo id, email order by recent place order
	//3. place  order
	//4. cancel order

	@Override
	public List<OrderResponse> getAllOrdersByEmail(String email) {
		try {
			List<OrderResponse> orders = orderRepository.findByEmail(email).stream()
					.map(order -> fromEntity(order)).collect(Collectors.toList());
			return orders;
		} catch (ResourceNotFoundException e) {
			System.out.println(String.format("User with email %s has not been placed any order", email));
			return null;
		}
	}

	@Override
	public OrderResponse getOrderById(String id, String email) {
		try {
			Order savedOrder = orderRepository.findByOrderIdAndUserEmailSortedByRecentOrderDate(id, email);
			return fromEntity(savedOrder);
		} catch (APIException e) {
			System.out.println(String.format("Order with id %d and email %s not found", id, email));
			return null;
		}
	}

	@Override
	@Transactional
	@Modifying
	public OrderResponse placeOrder(OrderRequest request, String email) {
		Order order = new Order();
		 String orderId = generateOrderId();
		 order.setId(orderId);
		 order.setShippingStatus(String.valueOf(ShippingStatus.PREPARING));
		 order.setShipping(request.getShipping());
		 order.setAddress(request.getAddress());
		 order.setPhone(request.getPhone());
		 order.setUser(userRepository.findByEmail(email).get());
		 Order savedOrder = orderRepository.save(order);
		 
		 List<CartItemDto> cartItemDtos = request.getCartItems();
		 List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		 for (CartItemDto item : cartItemDtos) {
			OrderDetail o = new OrderDetail();
			o.setQuantity(item.getQuantity());
			o.setProduct(productRepository.findById(item.getProduct().getId()).get());
			o.setOrder(savedOrder);
			orderDetailRepository.save(o);
			orderDetails.add(o);
			cartItemRepository.deleteById(item.getId());
		}
		 return fromEntity(savedOrder);
	}	

	//hủy đơn 
	@Override
	@Transactional
	public boolean deleteOrder(String id, String email) {
		try {
			User user = userRepository.findByEmail(email).get();
			Role role = roleRepository.findByName(String.valueOf(RoleName.ROLE_DIRE)).get();
			if(user.getRoles().contains(role)) {
				orderRepository.deleteById(id);
			}else {
				Order order = orderRepository.findByOrderIdAndUserEmail(id, email);
				if(order.getShippingStatus() == String.valueOf(ShippingStatus.PREPARING)) {
					orderRepository.delete(order);
				}else return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public OrderResponse fromEntity(Order savedOrder) {
		OrderResponse response = new OrderResponse();
		response.setId(savedOrder.getId());
		response.setShippingStatus(savedOrder.getShippingStatus());
		response.setSubtotal(savedOrder.getSubtotal());
		response.setShipping(savedOrder.getShipping());
		response.setTotal(savedOrder.getShipping() + savedOrder.getSubtotal());
		response.setFullname(savedOrder.getUser().getFullname());
		response.setOrderDate(savedOrder.getOrderDate());
		response.setAddress(savedOrder.getAddress());
		response.setPhone(savedOrder.getPhone());

		List<OrderDetailDto> orderDetailDtos = savedOrder.getOrderDetails().stream().
				map(OrderDetailDto :: fromEntity).collect(Collectors.toList());
		response.setOrderDetails(orderDetailDtos);
		
		return response;
	}
	
	
	 public String generateOrderId() {
		 String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	        int LENGTH = 10;
	        StringBuilder sb = new StringBuilder(LENGTH);

	        Random random = new Random();
	        for (int i = 0; i < LENGTH; i++) {
	            int randomIndex = random.nextInt(CHARACTERS.length());
	            char randomChar = CHARACTERS.charAt(randomIndex);
	            sb.append(randomChar);
	        }

	        String orderId = sb.toString();
	        while (orderRepository.existsById(orderId)) { // Kiểm tra xem ID đã tồn tại trong cơ sở dữ liệu chưa
	            sb.setLength(0); // Xóa chuỗi để tạo lại
	            for (int i = 0; i < LENGTH; i++) {
	                int randomIndex = random.nextInt(CHARACTERS.length());
	                char randomChar = CHARACTERS.charAt(randomIndex);
	                sb.append(randomChar);
	            }
	            orderId = sb.toString();
	        }
	        return orderId;
	 }

}