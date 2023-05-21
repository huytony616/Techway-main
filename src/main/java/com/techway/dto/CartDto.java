package com.techway.dto;

import java.util.ArrayList;
import java.util.List;

import com.techway.entity.Cart;
import com.techway.entity.CartItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto {
	
	private Long id;
	
	private String email;

    private List<CartItemDto> cartItems;

    private Double totalValue;
    
    public static CartDto fromEntity(Cart cart) {
    	CartDto cartDto = new CartDto();
    	cartDto.setId(cart.getId());
    	cartDto.setEmail(cart.getUser().getEmail());
        cartDto.setTotalValue(cart.getTotalValue());

        List<CartItemDto> cartItemDTOs = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()) {
            CartItemDto cartItemDTO = CartItemDto.fromEntity(cartItem);
            cartItemDTOs.add(cartItemDTO);
        }
        cartDto.setCartItems(cartItemDTOs);

        return cartDto;
    }

    public Cart toEntity() {
        Cart cart = new Cart();
        cart.setId(this.id);

        List<CartItem> cartItems = new ArrayList<>();
        for (CartItemDto cartItemDTO : this.cartItems) {
            CartItem cartItem = cartItemDTO.toEntity();
            cartItem.setCart(cart);
            cartItems.add(cartItem);
        }
        cart.setCartItems(cartItems);

        return cart;
    }
}





