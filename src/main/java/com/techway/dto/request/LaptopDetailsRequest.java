package com.techway.dto.request;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LaptopDetailsRequest {
	private long id;
	// Bộ xử lý
	private String cpu; // cong nghe CPU
	private int core; // số nhân
	private int thread; // số luồng
	private float cpuSpeed; // Tốc độ CPU(GHz)
	private float cpuMaxSpeed; // Tốc độ tối đa(Turbo Boost<GHz>)
	private int cache; // Bộ nhớ đệm(MB)

	// Bộ nhớ RAM, Ổ cứng
	private int ram; // RAM
	private String type; // loại RAM
	private int busRAM; // Tốc độ Bus RAM
	private int maxRAM; // Hỗ trợ RAM tối đa
	private String ssd;

	// Màn hình
	private float screenWidth; // Màn hình(inch)
	String screenResolution; // do phan giai man hinh
	private int hz;
	
	// cong nghệ màn hình
	private Set<Long> screenTechs;
	
	// Đồ họa và Âm thanh
	private String screenCard; // card màn hình
	private String sound; // Công nghệ âm thanh
}
