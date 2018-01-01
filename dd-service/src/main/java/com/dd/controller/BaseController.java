package com.dd.controller;

import java.util.HashMap;
import java.util.Map;


import com.dd.om.Users;
import com.dd.utils.Constants;

public class BaseController {

	public static Map<String, Object> map = new HashMap<>();
	
	public Users getLoggedInUser(){
		 return (Users) map.get(Constants.USER);
	}
	
	public static void setLoggerInUser(Users user){
		map.put(Constants.USER, user);
	}
	
	public static void logOutUser(){
		map.remove(Constants.USER);
	}
}
