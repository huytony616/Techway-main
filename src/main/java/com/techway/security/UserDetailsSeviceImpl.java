package com.techway.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techway.entity.User;
import com.techway.exception.ResourceNotFoundException;
import com.techway.repository.UserRepository;

@Service
public class UserDetailsSeviceImpl implements UserDetailsService{
	@Autowired
	UserRepository userRepository;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username).orElseThrow(
				() -> new ResourceNotFoundException(String.format("User with email %s not found", username))
				);				
		return UserDetailsImpl.build(user);
	}

}
