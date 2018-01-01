package com.dd.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.dd.dao.SecurityRepository;
import com.dd.om.Users;

public class UserService {
	
	@Autowired
	private SecurityRepository securityRepository;

	public Users getRegisteredUser(String email){
		return securityRepository.findByEmail(email);
	}
}
