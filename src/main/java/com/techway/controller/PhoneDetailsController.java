package com.techway.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techway.dto.request.PhoneDetailRequest;
import com.techway.entity.PhoneDetails;
import com.techway.service.PhoneDetailService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/phone-details")
public class PhoneDetailsController {
	@Autowired
	PhoneDetailService phoneDetailService;
	
	@GetMapping
	public ResponseEntity<List<PhoneDetails>> getAll() {
		List<PhoneDetails> list = new ArrayList<>();
		phoneDetailService.findAll().forEach(list::add);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<PhoneDetails> getOne(@PathVariable("id") long id) {
		return new ResponseEntity<PhoneDetails>(phoneDetailService.findById(id), HttpStatus.OK);
	}

	@PostMapping("{productId}")
	public ResponseEntity<PhoneDetails> create(@PathVariable("productId") long id,
			@RequestBody PhoneDetailRequest request) {
		return new ResponseEntity<PhoneDetails>(phoneDetailService.save(id, request), HttpStatus.CREATED);
	}
	@PutMapping("{productId}")
	public ResponseEntity<PhoneDetails> update(@PathVariable("productId") long id,
			@RequestBody PhoneDetailRequest request) {
		return new ResponseEntity<PhoneDetails>(phoneDetailService.update(id, request), HttpStatus.OK);
	}
	
	
	
}
