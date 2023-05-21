package com.techway.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.entity.SpecialFeature;
import com.techway.exception.ResourceNotFoundException;
import com.techway.repository.SpecialFeatureRepository;
import com.techway.service.SpecialFeatureService;

@Service
public class SpecialFeatureServiceImpl implements SpecialFeatureService{
	@Autowired
	private SpecialFeatureRepository repository;
	@Override
	public List<SpecialFeature> getAllSpecialFeatures() {
		return repository.findAll();
	}

	@Override
	public Optional<SpecialFeature> getSpecialFeatureById(int id) {
		return repository.findById(id);
	}

	@Override
	public SpecialFeature create(SpecialFeature specialFeature) {
		return repository.save(specialFeature);
	}
	

	@Override
	public void deleteSpecialFeature(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SpecialFeature update(int id, SpecialFeature specialFeature) {
		SpecialFeature savedSpecialFeature = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("SpecialFeature with id %d not found", id))
				);
		savedSpecialFeature.setName(specialFeature.getName());
		savedSpecialFeature.setPhones(specialFeature.getPhones());
		return repository.save(savedSpecialFeature);
	}

}
