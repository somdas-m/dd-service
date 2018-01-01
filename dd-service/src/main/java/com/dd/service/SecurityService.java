package com.dd.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dd.controller.BaseController;
import com.dd.dao.SecurityRepository;
import com.dd.om.Users;
import com.dd.utils.Constants;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

@Service
public class SecurityService {
	
	@Autowired
	private SecurityRepository securityRepository;

	public void authenticate(String idTokenString){
		Map<String, String> response = new HashMap<>();
		try{
			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance())
		    .setAudience(Collections.singletonList(Constants.CLIENT_ID))
		    // Or, if multiple clients access the backend:
		    //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
		    .build();

		GoogleIdToken idToken = verifier.verify(idTokenString);
		if (idToken != null) {
		  Payload payload = idToken.getPayload();

		  // Print user identifier
		  String userId = payload.getSubject();
		  System.out.println("User ID: " + userId);

		  // Get profile information from payload
		  String email = payload.getEmail();
		  boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
		  String name = (String) payload.get("name");
		  String pictureUrl = (String) payload.get("picture");
		  String locale = (String) payload.get("locale");
		  String familyName = (String) payload.get("family_name");
		  String givenName = (String) payload.get("given_name");
		  
		  Users user = securityRepository.findByEmail(email);

		  if(user!=null){
			 BaseController.setLoggerInUser(user);
			 System.out.println("Saved user");
			 response.put(Constants.STATUS_CODE,"200");
		  }else{
			  response.put(Constants.MESSAGE, "You have not registered");
			  response.put(Constants.STATUS_CODE,"404");
		  }
		  // Use or store profile information
		  // ...

		} else {
		  response.put(Constants.MESSAGE, "Token Expired. Kindly login to continue.");
		  response.put(Constants.STATUS_CODE, "401");
		}

			
		}catch (GeneralSecurityException | IOException e1) {
			 response.put(Constants.MESSAGE, "Internal Server Error.");
			 response.put(Constants.STATUS_CODE, "500");
		}
	}
	
	public Users getUserById(Long id){
		return securityRepository.findOne(id);
	}
	
}
