package com.techway.dto;

import com.techway.entity.CartItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
	private Long id;
	private Integer quantity;
    private ProductDto product;
    
    public static CartItemDto fromEntity(CartItem cartItem) {
        CartItemDto cartItemDTO = new CartItemDto();
        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setProduct
        (ProductDto.fromEntity(cartItem.getProduct()));
        return cartItemDTO;
    }

    public CartItem toEntity() {
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(this.quantity);
        cartItem.setProduct(this.product.toEntity());
        return cartItem;
    }
}
