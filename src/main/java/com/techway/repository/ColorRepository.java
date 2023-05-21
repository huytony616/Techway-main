package com.techway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techway.entity.Color;

public interface ColorRepository extends JpaRepository<Color, Integer>{

}
