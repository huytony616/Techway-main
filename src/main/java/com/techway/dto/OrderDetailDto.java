package com.techway.dto;

import com.techway.entity.OrderDetail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDto {
	private Long id;
	private Integer quantity;
	private ProductDto product;

	public static OrderDetailDto fromEntity(OrderDetail orderDetail) {
		OrderDetailDto dto = new OrderDetailDto();
		dto.setId(orderDetail.getId());
		dto.setQuantity(orderDetail.getQuantity());
		dto.setProduct(ProductDto.fromEntity(orderDetail.getProduct()));
		return dto;
	}

	public OrderDetail toEntity() {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setId(this.getId());
		orderDetail.setQuantity(this.getQuantity());
		orderDetail.setProduct(this.getProduct().toEntity());
		return orderDetail;
	}
}
