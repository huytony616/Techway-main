package com.techway.service;

import java.util.List;

import com.techway.entity.CameraFeature;

public interface CameraService {

	List<CameraFeature> findAll();

	CameraFeature findById(Integer id);

	CameraFeature save(CameraFeature o);

	Boolean deleteById(Integer id);

}
