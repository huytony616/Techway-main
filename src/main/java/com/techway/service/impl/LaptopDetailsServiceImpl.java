package com.techway.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.dto.request.LaptopDetailsRequest;
import com.techway.entity.DisplayTechnology;
import com.techway.entity.LaptopDetails;
import com.techway.exception.ResourceNotFoundException;
import com.techway.repository.LaptopDetailRepository;
import com.techway.repository.ProductRepository;
import com.techway.repository.DisplayTechRepository;
import com.techway.service.LaptopDetailsService;

@Service
public class LaptopDetailsServiceImpl implements LaptopDetailsService{
	@Autowired
	private LaptopDetailRepository laptopDetailRepository;

	@Autowired
	private DisplayTechRepository displayTechRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<LaptopDetails> findAll() {
		List<LaptopDetails> list = laptopDetailRepository.findAll();
//				.stream().map(o -> entityToResponse(o)).collect(Collectors.toList());
		return list;
	}
	
	@Override
	public LaptopDetails save(long productId, LaptopDetailsRequest request) {
		LaptopDetails laptopDetails = new LaptopDetails();
		request.setId(productId);
		LaptopDetails savedLaptopDetail = laptopDetailRepository.save(requestToEntity(request, laptopDetails));
		return savedLaptopDetail ;
	}
	
	@Override
	public LaptopDetails findById(long id) {
		LaptopDetails o = laptopDetailRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(
						String.format("LaptopDetail with id %d not found", id))
				);
		
		return o;
	}

	@Override
	public LaptopDetails update(long id, LaptopDetailsRequest request) {
		LaptopDetails laptopDetails = laptopDetailRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(
						String.format("LaptopDetail with id %d not found", id))
				);
		LaptopDetails updatedLaptopDetail = laptopDetailRepository.save(requestToEntity(request, laptopDetails));
		return updatedLaptopDetail;
	}
	
	private LaptopDetails requestToEntity(LaptopDetailsRequest request, LaptopDetails entity) {
		entity.setProduct(productRepository.findById(request.getId()).get());
		entity.setCpu(request.getCpu());
		entity.setCore(request.getCore());
		entity.setThread(request.getThread());
		entity.setCpuSpeed(request.getCpuSpeed());
		entity.setCpuMaxSpeed(request.getCpuMaxSpeed());
		entity.setCache(request.getCache());
		entity.setRam(request.getRam());
		entity.setTypeRAM(request.getType());
		entity.setBusRAM(request.getBusRAM());
		entity.setMaxRAM(request.getMaxRAM());
		entity.setSsd(request.getSsd());
		entity.setScreenSize(request.getScreenWidth());
		entity.setDisplayResolution(request.getScreenResolution());
		entity.setHz(request.getHz());
		Set<DisplayTechnology> set = new HashSet<DisplayTechnology>();
		Set<Long> setIds = request.getScreenTechs();
		setIds.stream().forEach(id -> set.add(displayTechRepository.findById(id).get()));
		entity.setDisplayTechnologies(set);
		entity.setGraphicsCard(request.getScreenCard());
		entity.setSound(request.getSound());
		return entity;
	}
	
//	private LaptopDetail entityToResponse(LaptopDetail o) {
//		Product product = productService.findById(o.getId());		
//		LaptopDetail details = new LaptopDetail();
//		details.setProduct(product);
//		details.setProduct(o.getProduct());
//		details.setCpu(o.getCpu());
//		details.setCore(o.getCore());
//		details.setThread(o.getThread());
//		details.setCpuSpeed(o.getCpuSpeed());
//		details.setCpuMaxSpeed(o.getCpuMaxSpeed());
//		details.setCache(o.getCache());
//		details.setRam(o.getRam());
//		details.setType(o.getType());
//		details.setBusRAM(o.getBusRAM());
//		details.setMaxRAM(o.getMaxRAM());
//		details.setSsd(o.getSsd());
//		details.setScreenWidth(o.getScreenWidth());
//		details.setScreenResolution(o.getScreenResolution());
//		details.setHz(o.getHz());
//		Set<ScreenTech> set = new HashSet<ScreenTech>();
//		screenTechRepository.findScreenTechsByLaptopsId(o.getId()).forEach(
//				set::add
//				);
//		details.setScreenTechs(set);
//		details.setScreenCard(o.getScreenCard());
//		details.setSound(o.getSound());
//		
//		return details;
//	}
	
	
}
