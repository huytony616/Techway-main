package com.techway.service;

import java.util.List;

import com.techway.dto.request.PhoneDetailRequest;
import com.techway.entity.PhoneDetails;

public interface PhoneDetailService {

	List<PhoneDetails> findAll();

	PhoneDetails findById(long id);

	PhoneDetails save(long id, PhoneDetailRequest request);

	PhoneDetails update(long id, PhoneDetailRequest request);

}
