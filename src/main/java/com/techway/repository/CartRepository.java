package com.techway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techway.entity.Cart;
import com.techway.entity.User;

public interface CartRepository extends JpaRepository<Cart, Long>{

	@Query("SELECT c FROM Cart c WHERE c.user = :user")
	Cart findByUser(@Param("user") User user);

	@Modifying
	@Query("DELETE FROM Cart c WHERE c.user = :user")
	void deleteByUser(@Param("user") User user);

}
