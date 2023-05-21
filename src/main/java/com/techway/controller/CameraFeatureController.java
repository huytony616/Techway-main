package com.techway.controller;

import java.util.List;

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

import com.techway.entity.CameraFeature;
import com.techway.service.CameraService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/camera-featurers")
public class CameraFeatureController {
	
	@Autowired
	private CameraService cameraService;
	
	@GetMapping
	public ResponseEntity<List<CameraFeature>> getList(){
		return new ResponseEntity<List<CameraFeature>>(cameraService.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<CameraFeature> getOne(@PathVariable("id") Integer id) {
		return new ResponseEntity<CameraFeature>(cameraService.findById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CameraFeature> create(@RequestBody CameraFeature o) {
		return new ResponseEntity<CameraFeature>(cameraService.save(o), HttpStatus.CREATED);
	}

	@PutMapping("{id}")
	public ResponseEntity<CameraFeature> update(@PathVariable("id") Integer id, @RequestBody CameraFeature o) {
		return new ResponseEntity<CameraFeature>(cameraService.save(o), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
		return new ResponseEntity<Boolean>(cameraService.deleteById(id), HttpStatus.OK);
	}
}
