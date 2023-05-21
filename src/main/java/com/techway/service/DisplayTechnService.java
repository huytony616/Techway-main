package com.techway.service;

import java.util.List;

import com.techway.entity.DisplayTechnology;

public interface DisplayTechnService {

	List<DisplayTechnology> findByNameContaining(String name);

	List<DisplayTechnology> findAll();

	DisplayTechnology findById(long id);

	DisplayTechnology save(DisplayTechnology o);

	DisplayTechnology update(long id, DisplayTechnology o);

	boolean deleteById(long id);

}
