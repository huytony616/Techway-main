package com.techway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.entity.CameraFeature;
import com.techway.exception.ResourceNotFoundException;
import com.techway.repository.CameraFeatureRepository;
import com.techway.service.CameraService;

@Service
public class CameraServiceImpl implements CameraService{
	@Autowired
	CameraFeatureRepository cameraFeatureRepository;
	@Override
	public List<CameraFeature> findAll() {
		List<CameraFeature> list = cameraFeatureRepository.findAll();
		return list;
	}

	@Override
	public CameraFeature findById(Integer id) {
		CameraFeature feature = cameraFeatureRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("CameraFeature with id %d not found", id))
				);
		return feature;
	}

	@Override
	public CameraFeature save(CameraFeature o) {
		return cameraFeatureRepository.save(o);
	}

	@Override
	public Boolean deleteById(Integer id) {
		CameraFeature feature = cameraFeatureRepository.findById(id).get();
		if(feature == null)
			return false;
		else
			cameraFeatureRepository.delete(feature);
		return true;
	}

}
