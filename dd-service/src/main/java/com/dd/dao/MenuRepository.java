package com.dd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dd.om.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {

	Menu findByName(String name);
	/*
	@Query("FROM Menu WHERE name = ?#([0]) AND id = ?#([1])")
	Menu findMENU(String name, Integer id);*/
}
