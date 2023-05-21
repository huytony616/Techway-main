package com.techway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techway.dto.CartDto;
import com.techway.dto.CartItemDto;
import com.techway.service.CartService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/cart")
public class CartController {
	
	//1. Lấy thông tin giỏ hàng theo User -> get: /api/v1/cart
	//2. Thêm sản phẩm vào giỏ hàng -> post: /api/v1/cart/product/:productId
	//3. cập nhật số lượng sản phẩm trong giỏ hàng(update CartItem) -> put: /api/v1/cart/:userId/update-amount
	//4. Xóa sản phẩm trong giỏ hàng -> delete: /api/v1/cart/remove-product/:productId
	//5. xóa toàn bộ sản phẩm trong giỏ hàng -> /api/v1/cart/clear-cart

    @Autowired
    private CartService cartService;
    
    // Lấy thông tin giỏ hàng theo User
    @GetMapping
    public ResponseEntity<CartDto> getCartByUser(Authentication authentication) {
    	String email = authentication.getName();// return email
    	System.out.println(email);
        CartDto cart = cartService.getCartByUserEmail(email);        
        return ResponseEntity.ok().body(cart);
    }

    // Thêm sản phẩm vào giỏ hàng
    @PostMapping("/product/{productId}")
    public ResponseEntity<Boolean> addProductToCart(@PathVariable("productId") Long productId, Authentication authentication) {
    	String email = authentication.getName();// return email
    	System.out.println(email);
        Boolean plusStatus = cartService.addProductToCart(email, productId);     
        System.out.println(plusStatus);
        return ResponseEntity.ok().body(plusStatus);
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    @PutMapping("/{userId}/update-amount")
    public ResponseEntity<CartDto> updateProductInCart(@RequestBody CartItemDto cartItemDto, Authentication authentication) {
    	String email = authentication.getName();
    	CartDto cartDTO = cartService.updateProductInCart(email, cartItemDto);
         if (cartDTO == null) {
             return ResponseEntity.notFound().build();
         }
         return ResponseEntity.ok().body(cartDTO);
    }

    // Xóa sản phẩm trong giỏ hàng
    @DeleteMapping("/remove-product/{productId}")
    public ResponseEntity<Void> removeProductFromCart(Authentication authentication, @PathVariable Long productId) {
        String email = authentication.getName();
        System.out.println(email);
    	cartService.deleteCartItem(email, productId);
        return ResponseEntity.noContent().build();
    }
    
    //Xóa toàn bộ sản phẩm trong giỏ hàng
    @DeleteMapping("/clear-cart")
    public ResponseEntity<Void> clearCart(Authentication authentication) {
        String email = authentication.getName();
        System.out.println(email);
    	cartService.clearCart(email);
        return ResponseEntity.noContent().build();
    }
    
}
