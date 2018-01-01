package com.dd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dd.om.Menu;
import com.dd.service.MenuService;

@RestController
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;

	@RequestMapping("/getAll")
	private Iterable<Menu> getMenu(){
		//TODO: Enable security
		return menuService.getMenu();
	}
}
