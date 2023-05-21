package com.techway.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techway.entity.SpecialFeature;
import com.techway.service.SpecialFeatureService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/special-features")
public class SpecialFeatureController {
	@Autowired
	private SpecialFeatureService specialFeatureService;

	@GetMapping
	public ResponseEntity<List<SpecialFeature>> getAllSpecialFeatures() {
		List<SpecialFeature> specialFeatures = specialFeatureService.getAllSpecialFeatures();
		return new ResponseEntity<>(specialFeatures, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SpecialFeature> getSpecialFeatureById(@PathVariable("id") int id) {
		Optional<SpecialFeature> specialFeature = specialFeatureService.getSpecialFeatureById(id);
		if (specialFeature.isPresent()) {
			return new ResponseEntity<>(specialFeature.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<SpecialFeature> addSpecialFeature(@RequestBody SpecialFeature specialFeature) {
		SpecialFeature savedSpecialFeature = specialFeatureService.create(specialFeature);
		return new ResponseEntity<>(savedSpecialFeature, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SpecialFeature> updateSpecialFeature(@PathVariable("id") int id,
			@RequestBody SpecialFeature specialFeature) {
		Optional<SpecialFeature> existingSpecialFeature = specialFeatureService.getSpecialFeatureById(id);
		if (existingSpecialFeature.isPresent()) {
			specialFeature.setId(id);
			SpecialFeature updatedSpecialFeature = specialFeatureService.update(id, specialFeature);
			return new ResponseEntity<>(updatedSpecialFeature, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteSpecialFeature(@PathVariable("id") int id) {
		Optional<SpecialFeature> specialFeature = specialFeatureService.getSpecialFeatureById(id);
		if (specialFeature.isPresent()) {
			specialFeatureService.deleteSpecialFeature(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
