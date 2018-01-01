package com.dd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dd.dao.MenuRepository;
import com.dd.om.Menu;

@RestController
public class TestController {

	@Autowired
	private MenuRepository menuRepository;
	
	@RequestMapping("/ping")
	public String ping(){
		return "success!";
	}
	
}
