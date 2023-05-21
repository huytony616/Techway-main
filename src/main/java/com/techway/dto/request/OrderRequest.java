package com.techway.dto.request;

import java.util.List;

import com.techway.dto.CartItemDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
	private List<CartItemDto> 
	cartItems;
	private double shipping; //phí ship
	private String address;
	private String phone;
}
