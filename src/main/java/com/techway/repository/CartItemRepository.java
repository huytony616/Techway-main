package com.techway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techway.entity.Cart;
import com.techway.entity.CartItem;
import com.techway.entity.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	@Query("SELECT ci FROM CartItem ci WHERE ci.product = :product AND ci.cart = :cart")
    CartItem findByProductAndCart(@Param("product") Product product, @Param("cart") Cart cart);

	@Modifying
    @Query("DELETE FROM CartItem ci WHERE ci.cart = :cart")
    void deleteByCart(@Param("cart") Cart cart);

	List<CartItem> findAllByCartId(Long id);
}
