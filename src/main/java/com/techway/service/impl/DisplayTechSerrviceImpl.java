package com.techway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.entity.DisplayTechnology;
import com.techway.exception.ResourceNotFoundException;
import com.techway.repository.LaptopDetailRepository;
import com.techway.repository.DisplayTechRepository;
import com.techway.service.DisplayTechnService;

@Service
public class DisplayTechSerrviceImpl implements DisplayTechnService {
	@Autowired
	DisplayTechRepository displayTechRepository;
	@Autowired
	LaptopDetailRepository laptopDetailRepository;

 	@Override
	public List<DisplayTechnology> findByNameContaining(String name) {
		return displayTechRepository.findByNameContaining(name);
	}

	@Override
	public List<DisplayTechnology> findAll() {
		return displayTechRepository.findAll();
	}

	@Override
	public DisplayTechnology findById(long id) {
		return displayTechRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("ScreenTech with id %d not found", id))
				);
	}

	@Override
	public DisplayTechnology save(DisplayTechnology o) {
		return displayTechRepository.save(o);
	}

	@Override
	public DisplayTechnology update(long id, DisplayTechnology o) {
		DisplayTechnology savedScreenTech = this.findById(id);
		savedScreenTech.setName(o.getName());
		return displayTechRepository.save(savedScreenTech);
	}

	@Override
	public boolean deleteById(long id) {
		DisplayTechnology o = this.findById(id);
		displayTechRepository.deleteById(o.getId());
		return true;
	}
	
	

}
