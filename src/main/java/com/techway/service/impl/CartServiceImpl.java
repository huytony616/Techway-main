package com.techway.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.dto.CartDto;
import com.techway.dto.CartItemDto;
import com.techway.entity.Cart;
import com.techway.entity.CartItem;
import com.techway.entity.Product;
import com.techway.entity.User;
import com.techway.repository.CartItemRepository;
import com.techway.repository.CartRepository;
import com.techway.repository.ProductRepository;
import com.techway.repository.UserRepository;
import com.techway.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;

	@Override
	public CartDto getCartByUserEmail(String email) {
		User user = userRepository.findByEmail(email).orElse(null);
		if (user == null) {
			return null;
		}
		Cart cart = cartRepository.findByUser(user);
		if (cart == null) {
			cart = new Cart();
			cart.setUser(user);
			cart = cartRepository.save(cart);
		}
		CartDto cartDto = CartDto.fromEntity(cart);
		return cartDto;
	}

	@Override
	public Boolean addProductToCart(String email,Long productId) {
		User user = userRepository.findByEmail(email).orElse(null);
		Product product = productRepository.findById(productId).orElse(null);
		if (user == null || product == null) {
			return false;
		}
		Cart cart = cartRepository.findByUser(user);
		if (cart == null) {
			cart = new Cart();
			cart.setUser(user);			
			cart = cartRepository.save(cart);
			CartItem cartItem = new CartItem();
			cartItem.setQuantity(1);
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItemRepository.save(cartItem);
		}else {
			CartItem cartItem = cartItemRepository.findByProductAndCart(product, cart);
			if(cartItem != null) {
				cartItem.setQuantity(cartItem.getQuantity() + 1);
				cartItemRepository.save(cartItem);
			}else {
				cartItem = new CartItem();
				cartItem.setQuantity(1);
				cartItem.setProduct(product);
				cartItem.setCart(cart);
				cartItemRepository.save(cartItem);
			}
		}
		return true;
	}

	@Override
	public void deleteCartItem(String email, Long productId) {
		Optional<User> userOptional = userRepository.findByEmail(email);

        if (!userOptional.isPresent()) {
            return;
        }

        User user = userOptional.get();
        Cart cart = cartRepository.findByUser(user);

        if (cart == null) {
            return;
        }

        Optional<CartItem> cartItemOptional = cart.getCartItems().stream()
                .filter(ci -> ci.getProduct().getId().equals(productId))
                .findFirst();

        if (!cartItemOptional.isPresent()) {
            return;
        }

        CartItem cartItem = cartItemOptional.get();
        cart.getCartItems().remove(cartItem);
        cartRepository.save(cart);
	}

	

	@Override
	public void clearCart(String email) {
		User user = userRepository.findByEmail(email).get();
		Cart cart = cartRepository.findByUser(user);
//		cartItemRepository.deleteByCart(cart);	
		List<CartItem> cartitems = cartItemRepository.findAllByCartId(cart.getId());
		System.out.println(cartitems);
		for (CartItem o : cartitems) {
			o.setCart(null);
			cartItemRepository.save(o);
		}
	}

	@Override
	public CartDto updateProductInCart(String email, CartItemDto cartItemDto) {
	       User user = userRepository.findByEmail(email).get();

	        Cart cart = cartRepository.findByUser(user);

	        if (cart == null) {
	            return null;
	        }

	        Optional<CartItem> cartItems = cart.getCartItems().stream()
	                .filter(ci -> ci.getProduct().getId().equals(cartItemDto.getProduct().getId()))
	                .findFirst();

	        if (!cartItems.isPresent()) {
	            return null;
	        }

	        CartItem cartItem = cartItems.get();
	        cartItem.setQuantity(cartItemDto.getQuantity());    

	        Cart savedCart = cartRepository.save(cart);
	        CartDto savedCartDTO = CartDto.fromEntity(savedCart);

	        return savedCartDTO;
	}



//	private Double calculateTotalValue(Cart cart) {
//		Double totalValue = 0.0;
//
//        for (CartItem cartItem : cart.getCartItems()) {
//            Double itemValue = cartItem.getQuantity() * cartItem.getProduct().getPrice();
//            totalValue += itemValue;
//        }
//
//        return totalValue;
//	}

	
}
