package com.techway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techway.entity.LaptopDetails;

public interface LaptopDetailRepository extends JpaRepository<LaptopDetails, Long>{
	List<LaptopDetails> findLaptopDetailsByDisplayTechnologies_Id(Long displayTechnologyId);
}
