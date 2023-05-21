package com.techway.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techway.dto.AccountDto;
import com.techway.dto.RegistrationDTO;
import com.techway.entity.Role;
import com.techway.entity.User;
import com.techway.exception.ResourceNotFoundException;
import com.techway.repository.RoleRepository;
import com.techway.repository.UserRepository;
import com.techway.service.UserService;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
    private UserRepository userRepository;
     
    @Autowired
    private PasswordEncoder passwordEncoder;
     
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    RoleRepository roleRepository;

    @Transactional
    public void register(RegistrationDTO dto, String siteURL)
            throws UnsupportedEncodingException, MessagingException {
    	User user = registrationDtoToEntity(dto);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
         
        String randomCode = RandomString.make(50);
        user.setVerificationCode(randomCode);
        user.setEnabled(false);
         
        userRepository.save(user);
         
        sendVerificationEmail(user, siteURL);
    }
    
    @Override
    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);
        
        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);             
            return true;
        }
         
    }
    
    
    @Override
    public String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        System.out.println(request.getServletPath());
        System.out.println(siteURL);
        System.out.println(siteURL.replace(request.getServletPath(), ""));
        return siteURL.replace(request.getServletPath(), "");
        
    }  
    
	
    @Override
	public List<AccountDto> findAll() {
		List<User> users = userRepository.findAll();
		List<AccountDto> list=  users.stream().map(acc -> entityToAccountDto(acc)).collect(Collectors.toList());		
		return list;
    }
    
    @Override
    public AccountDto get(Long id) {
        User user = userRepository.findById(id).get();
        return entityToAccountDto(user);
    }
    
    
	@Override
    public AccountDto findByEmail(String email) {
    	User user = userRepository.findByEmail(email).orElseThrow(
				() -> new ResourceNotFoundException(String.format("User with email %d not found", email))
				);
		return entityToAccountDto(user);
    }

	@Override
	public AccountDto updateAccount(Long accountId, AccountDto account) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void sendVerificationEmail(User user, String siteURL)
	        throws MessagingException, UnsupportedEncodingException {
	    String toAddress = user.getEmail();
	    String fromAddress = "techway.com";
	    String senderName = "Techway";
	    String subject = "Please verify your registration";
	    String content = "Dear [[name]],<br>"
	            + "Please click the link below to verify your registration:</br>"
	            + "<h3><a href=\"[[URL]]\">VERIFY</a></h3>"
	            + "Thank you,</br>"
	            + "Techway";
//	    target=\"_self\"
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);
	     
	    content = content.replace("[[name]]", user.getFullname());
	    String verifyURL = siteURL + "/api/v1/auth/verify?code=" + user.getVerificationCode();
	     
	    content = content.replace("[[URL]]", verifyURL);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
	     
	}
    
    private User registrationDtoToEntity(RegistrationDTO dto) {
    	Role role = roleRepository.findByName("ROLE_CUST").orElseThrow(
    			() -> new ResourceNotFoundException(String.format("Role %s not exist", "ROLE_CUST"))
    			);
    	User user = new User();
    	user.setEmail(dto.getEmail());
    	user.setPassword(dto.getPassword());
    	user.setFullname(dto.getFullname());
    	user.setPhoto(dto.getPhoto());
    	user.setEnabled(false);
    	user.addRole(role);
    	return user;
    }
    
    private AccountDto entityToAccountDto(User savedAccount) {
    	AccountDto accountDto = new AccountDto();
    	accountDto.setId(savedAccount.getId());
    	accountDto.setFullName(savedAccount.getFullname());
    	accountDto.setEmail(savedAccount.getEmail());
    	accountDto.setPhoto(savedAccount.getPhoto());
    	accountDto.setRoles(savedAccount.getRoles().stream().map(
    			Role::getName).collect(Collectors.toSet()));    	
    	return accountDto;
    }
 

	@Override
	public AccountDto findById(Long id) {
		User user = userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("User with id %d not found", id))
				);
		return entityToAccountDto(user);
	}


}
