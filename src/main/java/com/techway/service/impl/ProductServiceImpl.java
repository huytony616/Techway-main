package com.techway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.dto.request.ProductRequest;
import com.techway.entity.Color;
import com.techway.entity.Product;
import com.techway.exception.ResourceNotFoundException;
import com.techway.repository.CategoryRepository;
import com.techway.repository.ColorRepository;
import com.techway.repository.ManufacturerRepository;
import com.techway.repository.ProductRepository;
import com.techway.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ManufacturerRepository manufacturerRepository;
	@Autowired
	ColorRepository colorRepository;

	@Override
	public List<Product> findByNameContaining(String name) {
		List<Product> products = productRepository.findByNameContaining(name);
		return products;
	}
	
	@Override
	public List<Product> findAll() {
		List<Product> products = productRepository.findAll();
		return products;
	}
	
	@Override
	public List<Product> findByAvailable() {
		List<Product> products = productRepository.findByAvailable(true);
		return products;
	}

	@Override
	public Product findById(long id) {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Product with id %d not found", id))
				);
		return product;
	}

	@Override
	public List<Product> findAllByCategoryId(int cid) {
		List<Product> products = productRepository.findAllByCategoryId(cid);
		return products;
	}

	@Override
	public boolean disable(long id) {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Product Id %d not found", id))
				);
		product.setAvailable(false);
		productRepository.save(product);
		return true;
	}

	@Override
	public Product save(ProductRequest productRequest) {
		Product product = new Product();
		Product savedProduct = productRepository.save(productDtoToEntity(productRequest, product));
		return savedProduct;
	}
	
	@Override
	public Product update(long id, ProductRequest productRequest) {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Product Id %d not found", id))
				);
		product.setProductNo(productRequest.getProductNo());
		product.setName(productRequest.getName());
		product.setImages(productRequest.getImage());
		product.setPrice(productRequest.getPrice());
		product.setAvailable(productRequest.getAvailable());
		product.setCategory(categoryRepository.findById(productRequest.getCategoryId()).get());
		product.setManufacturer(manufacturerRepository.findById(productRequest.getManufacturerId()).get());
		product.setColor(colorRepository.findById(productRequest.getColorId()).get());
		productRepository.save(product);		
		return product;
	}	

	private Product productDtoToEntity(ProductRequest productRequest, Product product) {
		product.setProductNo(productRequest.getProductNo());
		product.setName(productRequest.getName());
		product.setImages(productRequest.getImage());
		product.setPrice(productRequest.getPrice());
		product.setAvailable(productRequest.getAvailable());
		product.setCreatedDate(productRequest.getCreatedDate());
		product.setCategory(categoryRepository.findById(productRequest.getCategoryId()).get());
		product.setManufacturer(manufacturerRepository.findById(productRequest.getManufacturerId()).get());
		product.setColor(colorRepository.findById(productRequest.getColorId()).get());
		return product;
	}

	@Override
	public List<Color> getColors(String productNo) {
		List<Color> colors =productRepository.findColorsByProductNo(productNo);
		System.out.println(colors);
		return colors;
	}


//	private ProductRequest entityToProductDto(Product savedProduct) {
//		ProductRequest productRequest = new ProductRequest();
//		productRequest.setId(savedProduct.getId());
//		productRequest.setName(savedProduct.getName());
//		productRequest.setImage(savedProduct.getImages());
//		productRequest.setPrice(savedProduct.getPrice());
//		productRequest.setAvailable(savedProduct.getAvailable());
//		productRequest.setCreatedDate(savedProduct.getCreatedDate());
//		productRequest.setCategoryId(savedProduct.getCategory().getId());
//		productRequest.setManufacturerId(savedProduct.getManufacturer().getId());
//		productRequest.setColorId(savedProduct.getColor().getId());
//		return  productRequest;
//	}
	
//	private Product entityToProductResponse(Product entity) {
//		ProductResponse response = new ProductResponse();
//		response.setId(entity.getId());
//		response.setProductNo(entity.getProductNo());
//		response.setName(entity.getName());
//		response.setImages(entity.getImages());
//		response.setPrice(entity.getPrice());
//		response.setAvailable(entity.getAvailable());
//		response.setCreatedDate(entity.getCreatedDate());
//		response.setCategory(entity.getCategory());
//		response.setManufacturer(entity.getManufacturer());
//		response.setColor(entity.getColor());
//		return  response;
//	}

	

}
