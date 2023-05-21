package com.techway.service;

import java.util.List;

import com.techway.dto.request.LaptopDetailsRequest;
import com.techway.entity.LaptopDetails;

public interface LaptopDetailsService {

	List<LaptopDetails> findAll();

	LaptopDetails findById(long id);

	LaptopDetails save(long id, LaptopDetailsRequest request);

	LaptopDetails update(long id, LaptopDetailsRequest request);

}
