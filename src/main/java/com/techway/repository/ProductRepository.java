package com.techway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techway.entity.Color;
import com.techway.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{



	List<Product> findAllByCategoryId(int cid);

	List<Product> findByAvailable(Boolean available);

	List<Product> findByNameContaining(String name);

	
	@Query("SELECT p.color FROM Product p WHERE p.productNo = :productNo")
    List<Color> findColorsByProductNo(@Param("productNo") String productNo);


}
