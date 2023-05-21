package com.techway.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
	private Long id;

	@NotEmpty
	private String content;

	private String  createdBy;//input: email(Authentication), output: full name

	private Long productId;

	private Date createdDate;
	
}
