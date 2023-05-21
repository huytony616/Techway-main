package com.techway.dto.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.techway.dto.OrderDetailDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {
	private String id;
	private String shippingStatus;
	private double subtotal;
	private double shipping;//fee
	private double total;
	private String fullname; //of user
	private Date orderDate;
	private String address;
	private String phone;
	private List<OrderDetailDto> orderDetails = new ArrayList<>();
}
