package com.dd.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dd.om.Users;

public interface SecurityRepository extends JpaRepository<Users, Long> {
	Users findByEmail(String email);
}
