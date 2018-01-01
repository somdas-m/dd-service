package com.dd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dd.service.SecurityService;

@RestController
@RequestMapping("/security")
public class SecurityController extends BaseController {
	
	@Autowired
	private SecurityService securityService;
	
	@PostMapping("/login")
	public void login(String idTokenString){
		securityService.authenticate(idTokenString);
	}
	
	@GetMapping("/test")
	private String test(){
		return getLoggedInUser().getEmail();
	}

}
