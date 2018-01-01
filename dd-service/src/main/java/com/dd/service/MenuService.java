package com.dd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dd.dao.MenuRepository;
import com.dd.om.Menu;

@Service
@Transactional
public class MenuService {

	@Autowired 
	private MenuRepository menuRepository;
	
	public Iterable<Menu> getMenu() {
		return menuRepository.findAll();
	}

}
