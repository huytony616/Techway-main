package com.techway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techway.dto.TotalByCategoryAndMonthDto;
import com.techway.dto.TotalByMonth;
import com.techway.service.ReportService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/report")
//@PreAuthorize("hasRole'ROLE_DIRE'")
public class ReportController {
	@ Autowired
	 private  ReportService reportService;
	
	@GetMapping("/monthly-sales")
    public ResponseEntity<List<TotalByMonth>> getMonthlySales() {
        List<TotalByMonth> monthlySales = reportService.findMonthlySales();
        return ResponseEntity.ok(monthlySales);
    }
	
	@GetMapping("/total-by-category-month")
    public ResponseEntity<List<TotalByCategoryAndMonthDto>> getTotalByCategoryAndMonth() {
		List<TotalByCategoryAndMonthDto> monthlySales = reportService.findTotalByCategoryAndMonth();
        return ResponseEntity.ok(monthlySales);
    }
	
}
