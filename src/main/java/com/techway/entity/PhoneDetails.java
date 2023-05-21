package com.techway.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "phone_details")
public class PhoneDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	// @MapsId -> indicates that the primary key values
	// will be copied from the Product entity
	@OneToOne(fetch = FetchType.EAGER)
	@MapsId
	@JoinColumn(name = "product_id")
	private Product product;

	/*
	 * nhóm các thông số: 1-màn hình, 2-camera sau, 3-camera trước, 4-HDH, 5-Bộ nhớ
	 * & Lưu trữ, 6-Kết nối, 7-pin & sạc, 8-tiện ích, 9-thông tin chung 
	 * 
	 * 
	 */

	// Màn hình
	@ManyToOne()
	@JoinColumn(name = "screen_tech_id")
	private DisplayTechnology displayTechnology; // cong nghe man hinh
	private String displayResolution; // độ phân giải
	private float screenSize; // màn hình rộng
	private String maxLight; // độ sáng tối đa
	private String glass; // Mặt kính cảm ứng

	// Camera sau
	private String mainCameraResolution;// Độ phân giải camera sau
	private String selfieCameraResolution; //do phan giai camera sau
	private boolean flash;// Đèn Flash

	// danh sách các tính năng của camera sau
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "phone_main_cameras", joinColumns = {
			@JoinColumn(name = "phone_id") }, inverseJoinColumns = { @JoinColumn(name = "main_camera_id") })
	private Set<CameraFeature> mainCameraFeatures = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "phone_selfie_cameras", joinColumns = {
			@JoinColumn(name = "phone_id") }, inverseJoinColumns = { @JoinColumn(name = "selfie_camera_id") })
	private Set<CameraFeature> selfieCameraFeatures = new HashSet<>();

	// Hệ điều hành & CPU
	private String os;// Hệ điều hành
	private String chipset;// Chip xử lý (CPU)
	private String cpuSpeed;// Tốc độ CPU
	private String gpu;// Chip đồ họa (GPU)

	// Bộ nhớ & Lưu trữ
	int ram; // RAM
	int rom; // Dung lượng lưu trữ
	float romUseable;// Dung lượng còn lại (khả dụng) khoảng
	String contacts;// Danh bạ

	// Kết nối
	String mobileNetwork; // Mạng di động
	String sim; // SIM
	String bluetooth;// Bluetooth
	String port;// Cổng kết nối/sạc
	String jackPhone;// Jack tai nghe

	// Pin & Sạc
	int batteryCapacity;// Dung lượng pin
	String batteryType;// Loại pin
	int maxChargingSupport;// Hỗ trợ sạc tối đa

	// Tiện ích
	
	// Bảo mật nâng cao
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "phone_AdvancedSecurities", joinColumns = {
			@JoinColumn(name = "phone_id") }, inverseJoinColumns = { @JoinColumn(name = "security_id") })
	private Set<AdvancedSecurity> advancedSecurities = new HashSet<>();

	//tính năng đặc biệt
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "phone_special_features", joinColumns = {
			@JoinColumn(name = "phone_id") }, inverseJoinColumns = { @JoinColumn(name = "feature_id") })
	private Set<SpecialFeature> specialFeatures = new HashSet<>();

	// Thông tin chung
	String design; // Thiết kế
	String material;// Chất liệu
	String dimensionAndWeight;// Kích thước, khối lượng
	
	public void addMainCameraFeature(CameraFeature camera) {
		this.mainCameraFeatures.add(camera);
		camera.getMainCameraPhones().add(this);
	}

	public void removeMainCameraFeature(CameraFeature camera) {		
			this.mainCameraFeatures.remove(camera);
			camera.getMainCameraPhones().remove(this);
	}
	
	public void addSelfieCameraFeature(CameraFeature camera) {
		this.selfieCameraFeatures.add(camera);
		camera.getSelfieCameraPhones().add(this);
	}

	public void removeSelfieCameraFeature(CameraFeature camera) {		
			this.selfieCameraFeatures.remove(camera);
			camera.getSelfieCameraPhones().remove(this);
	}
	
	public void addAdvancedSecurity(AdvancedSecurity security) {
		this.advancedSecurities.add(security);
		security.getPhones().add(this);
	}

	public void removeAdvancedSecurity(AdvancedSecurity security) {		
			this.advancedSecurities.remove(security);
			security.getPhones().remove(this);
	}
	
	public void addSpecialFeature(SpecialFeature feature) {
		this.specialFeatures.add(feature);
		feature.getPhones().add(this);
	}

	public void removeSpecialFeature(SpecialFeature feature) {		
			this.specialFeatures.remove(feature);
			feature.getPhones().remove(this);
	}
}
