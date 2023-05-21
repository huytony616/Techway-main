package com.techway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techway.entity.DisplayTechnology;

public interface DisplayTechRepository extends JpaRepository<DisplayTechnology, Long> {
	List<DisplayTechnology> findByNameContaining(String name);
	List<DisplayTechnology> findScreenTechsByLaptopsId(Long productId);
}
