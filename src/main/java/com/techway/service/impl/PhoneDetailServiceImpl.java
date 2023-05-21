package com.techway.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.dto.request.PhoneDetailRequest;
import com.techway.entity.AdvancedSecurity;
import com.techway.entity.CameraFeature;
import com.techway.entity.PhoneDetails;
import com.techway.entity.SpecialFeature;
import com.techway.exception.ResourceNotFoundException;
import com.techway.repository.AdvancedSecurityRepository;
import com.techway.repository.CameraFeatureRepository;
import com.techway.repository.PhoneDetailRepository;
import com.techway.repository.ProductRepository;
import com.techway.repository.DisplayTechRepository;
import com.techway.repository.SpecialFeatureRepository;
import com.techway.service.PhoneDetailService;
@Service
public class PhoneDetailServiceImpl implements PhoneDetailService{
	@Autowired	private PhoneDetailRepository phoneDetailRepository;
	@Autowired	private ProductRepository productRepository;
	@Autowired	private DisplayTechRepository displayTechRepository;
	@Autowired	private CameraFeatureRepository cameraFeatureRepository;
	@Autowired	private AdvancedSecurityRepository advancedSecurityRepository;
	@Autowired private SpecialFeatureRepository specialFeatureRepository;
	

	@Override
	public List<PhoneDetails> findAll() {
		return phoneDetailRepository.findAll();
	}

	@Override
	public PhoneDetails findById(long id) {
		PhoneDetails o = phoneDetailRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("PhoneDetails with id %d not found", id))
				);
		return o;
	}

	@Override
	public PhoneDetails save(long id, PhoneDetailRequest request) {
		PhoneDetails o = new PhoneDetails();
		request.setId(request.getId());
		PhoneDetails savedPhoneDetails = phoneDetailRepository.save(requestToEntity(request, o));
		return savedPhoneDetails;
	}

	@Override
	public PhoneDetails update(long id, PhoneDetailRequest request) {
		PhoneDetails o = phoneDetailRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(
						String.format("PhoneDetails with id %d not found", id))
				);
		PhoneDetails updatedPhoneDetails = phoneDetailRepository.save(requestToEntity(request, o));
		return updatedPhoneDetails;
	}
	
	private PhoneDetails requestToEntity(PhoneDetailRequest request, PhoneDetails entity) {
		entity.setProduct(productRepository.findById(request.getId()).get());
		entity.setDisplayTechnology(displayTechRepository.findById(request.getScreenTech()).get());
		entity.setDisplayResolution(request.getScreenResolution());
		entity.setScreenSize(request.getScreenWidth());
		entity.setMaxLight(request.getMaxLight());
		entity.setGlass(request.getGlass());
		entity.setMainCameraResolution(request.getBackCameraResolution());
		entity.setSelfieCameraResolution(request.getFrontCameraResolution());
		entity.setFlash(request.isFlash());
		
		Set<CameraFeature> backcameraFeatures = new HashSet<CameraFeature>();
		request.getBackCameraFeatures().stream().forEach(
				id -> backcameraFeatures.add(cameraFeatureRepository.findById(id).get())
				);
		entity.setMainCameraFeatures(backcameraFeatures);
		
		Set<CameraFeature> frontCameraFeatures = new HashSet<CameraFeature>();
		request.getBackCameraFeatures().stream().forEach(
				id -> frontCameraFeatures.add(cameraFeatureRepository.findById(id).get())
				);
		entity.setSelfieCameraFeatures(frontCameraFeatures);
		
		entity.setOs(request.getOs());
		entity.setChipset(request.getCpu());
		entity.setCpuSpeed(request.getCpuSpeed());
		entity.setGpu(request.getGpu());
		entity.setRam(request.getRam());
		entity.setRom(request.getRom());
		entity.setRomUseable(request.getRomUseable());
		entity.setContacts(request.getContacts());
		entity.setMobileNetwork(request.getMobileNetwork());
		entity.setSim(request.getSim());
		entity.setBluetooth(request.getBluetooth());
		entity.setPort(request.getPort());
		entity.setJackPhone(request.getJackPhone());
		entity.setBatteryCapacity(request.getPinCapacity());
		entity.setBatteryType(request.getPinType());
		entity.setMaxChargingSupport(request.getMaxChargingSupport());
		
		Set<AdvancedSecurity> advancedSecurities = new HashSet<AdvancedSecurity>();
		request.getAdvancedSecurities().stream().forEach(
				id -> advancedSecurities.add(advancedSecurityRepository.findById(id).get())
				);
		entity.setAdvancedSecurities(advancedSecurities);
		
		Set<SpecialFeature> specialFeatures = new HashSet<SpecialFeature>();
		request.getSpecialFeatures().stream().forEach(
				id -> specialFeatures.add(specialFeatureRepository.findById(id).get())
				);
		entity.setSpecialFeatures(specialFeatures);
		
		entity.setDesign(request.getDesign());
		entity.setMaterial(request.getMaterial());
		entity.setDimensionAndWeight(request.getDimensionAndWeight());
		
		return entity;
	}

}
