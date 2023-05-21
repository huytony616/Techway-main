package com.techway.service;

import java.util.List;
import java.util.Optional;

import com.techway.entity.SpecialFeature;

public interface SpecialFeatureService {

	List<SpecialFeature> getAllSpecialFeatures();

	Optional<SpecialFeature> getSpecialFeatureById(int id);

	SpecialFeature create(SpecialFeature specialFeature);
	
	SpecialFeature update(int id, SpecialFeature specialFeature);

	void deleteSpecialFeature(int id);

}
