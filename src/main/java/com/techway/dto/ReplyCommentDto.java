package com.techway.dto;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyCommentDto {
	private long id;
	@NotNull
	private String content;
	private Date created_at;
	private String createdBy; //input:email(Authentication), output: full name
	private long commentId;
}
