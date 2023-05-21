package com.techway.service;

import com.techway.dto.CartDto;
import com.techway.dto.CartItemDto;

public interface CartService {
	
	public void deleteCartItem(String email, Long productId);

    public void clearCart(String email);

	public Boolean addProductToCart(String email, Long  productId);

	public CartDto updateProductInCart(String email, CartItemDto cartItemDto);

	public CartDto getCartByUserEmail(String email);

}
