package com.techway.service;

import java.util.List;

import com.techway.dto.TotalByCategoryAndMonthDto;
import com.techway.dto.TotalByMonth;

public interface ReportService {
	List<TotalByMonth> findMonthlySales();
	List<TotalByCategoryAndMonthDto> findTotalByCategoryAndMonth();
}
