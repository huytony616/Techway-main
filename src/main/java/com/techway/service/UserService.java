package com.techway.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.techway.dto.AccountDto;

public interface UserService {

	boolean verify(String verificationCode);
	String getSiteURL(HttpServletRequest request);
	List<AccountDto> findAll();
	AccountDto get(Long id);
	AccountDto updateAccount(Long accountId, AccountDto account);
	AccountDto findById(Long id);
	AccountDto findByEmail(String email);
	
}
