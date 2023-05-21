package com.techway.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techway.entity.DisplayTechnology;
import com.techway.service.DisplayTechnService;

@Controller
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/screentechs")
public class ScreenTechController {
	@Autowired
	DisplayTechnService displayTechnService;

	@GetMapping
	public ResponseEntity<List<DisplayTechnology>> getAll(@RequestParam(required = false) String name) {
		List<DisplayTechnology> list = new ArrayList<>();
		if (name != null) {
			displayTechnService.findByNameContaining(name).forEach(list::add);
		} else {
			displayTechnService.findAll().forEach(list::add);
		}
		if (list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DisplayTechnology> getById(@PathVariable("id") long id) {
		DisplayTechnology displayTechnology = displayTechnService.findById(id);
		if (displayTechnology == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(displayTechnology);
	}

	@PostMapping
	public ResponseEntity<DisplayTechnology> create(@RequestBody DisplayTechnology DisplayTechnology) {
		DisplayTechnology createdScreenTech = displayTechnService.save(DisplayTechnology);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdScreenTech);
	}

	@PutMapping("/{id}")
	public ResponseEntity<DisplayTechnology> update(@PathVariable("id") long id, @RequestBody DisplayTechnology displayTechnology) {
		DisplayTechnology updatedScreenTech = displayTechnService.update(id, displayTechnology);
		if (updatedScreenTech == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedScreenTech);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteById(@PathVariable("id") long id) {
		boolean isDeleted = displayTechnService.deleteById(id);
		if (isDeleted) {
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.notFound().build();
	}
}
