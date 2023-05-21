package com.techway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techway.entity.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer>{

}
