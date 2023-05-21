package com.techway.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.dto.TotalByCategoryAndMonthDto;
import com.techway.dto.TotalByMonth;
import com.techway.repository.OrderRepository;
import com.techway.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService{
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
    public List<TotalByMonth> findMonthlySales() {
        return orderRepository.findMonthlySales().stream().map(obj -> {
        	TotalByMonth dto = new TotalByMonth();
            dto.setMonth((Integer) obj[0]);
            dto.setTotal((Double) obj[1]);
            return dto;
        }).collect(Collectors.toList());
    }
    @Override
    public List<TotalByCategoryAndMonthDto> findTotalByCategoryAndMonth() {
        return orderRepository.findTotalByCategoryAndMonth().stream().map(obj -> {
            TotalByCategoryAndMonthDto dto = new TotalByCategoryAndMonthDto();
            dto.setCategory_id((Integer) obj[0]);
            dto.setMonth((Integer) obj[1]);
            dto.setTotal((Double) obj[2]);
            return dto;
        }).collect(Collectors.toList());
    }
}
