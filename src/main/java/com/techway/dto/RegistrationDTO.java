package com.techway.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistrationDTO {
	private Long id;
	private String email;
	private String password;
	private String fullname;
	private String photo;
}
