package com.techway.dto.request;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneDetailRequest {
	private Long id;
	private Long screenTech;
	private String screenResolution; 
	private float screenWidth; 
	private String maxLight; 
	private String glass; 
	private String backCameraResolution;
	private String frontCameraResolution;
	private boolean flash;
	private Set<Integer> backCameraFeatures = new HashSet<>();
	private Set<Integer> frontCameraFeatures = new HashSet<>();
	private String os;
	private String cpu;
	private String cpuSpeed;
	private String gpu;
	private int ram; 
	private int rom; 
	private float romUseable;
	private String contacts;
	private String mobileNetwork; 
	private String sim;
	private String bluetooth;
	private String port;
	private String jackPhone;
	private int pinCapacity;
	private String pinType;
	private int maxChargingSupport;
	private Set<Integer> advancedSecurities = new HashSet<>();
	private Set<Integer> specialFeatures = new HashSet<>();
	private String design; 
	private String material;
	private String dimensionAndWeight;
}
