package com.techway.dto.request;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ProductRequest {
	private long id;
	private String productNo;
	private String name;
	private String image;
	private double price;
	private Boolean available;
	private Date createdDate = new Date();
	private int categoryId;
	private int manufacturerId;
	private int colorId;

}
