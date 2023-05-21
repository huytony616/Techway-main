package com.techway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techway.entity.PhoneDetails;

public interface PhoneDetailRepository extends JpaRepository<PhoneDetails, Long>{

}
