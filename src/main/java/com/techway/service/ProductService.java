package com.techway.service;

import java.util.List;

import com.techway.dto.request.ProductRequest;
import com.techway.entity.Color;
import com.techway.entity.Product;

public interface ProductService {	
	Product findById(long id);
	List<Product> findAllByCategoryId(int cid);
	Product save(ProductRequest productRequest);
	Product update(long id, ProductRequest productRequest);
	boolean disable(long id);
	List<Product> findByAvailable();
	List<Product> findByNameContaining(String name);
	List<Color> getColors(String productNo);
	List<Product> findAll();
}
