package com.techway.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalByCategoryAndMonthDto {
	 private  int category_id;
	 private int month;
	 private double total;
}
