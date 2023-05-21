package com.techway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techway.entity.Manufacturer;
import com.techway.repository.ManufacturerRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/manufacturers")
public class ManufacturerController {
	@Autowired
	ManufacturerRepository manufacturerRepository;

	@GetMapping
	public List<Manufacturer> list(){
		return manufacturerRepository.findAll();
	}

	@GetMapping("{id}")
	public Manufacturer getOne(@PathVariable("id") Integer id) {
		return manufacturerRepository.findById(id).get();
	}

	@PostMapping
	public Manufacturer create(@RequestBody Manufacturer manufacturer) {
		return manufacturerRepository.save(manufacturer);
	}

	@PutMapping("{id}")
	public Manufacturer update(@PathVariable("id") Integer id, @RequestBody Manufacturer manufacturer) {
		return manufacturerRepository.save(manufacturer);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") int id) {
		manufacturerRepository.deleteById(id);
	}

}
