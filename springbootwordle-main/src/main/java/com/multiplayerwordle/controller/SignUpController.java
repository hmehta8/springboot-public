package com.multiplayerwordle.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multiplayerwordle.pojo.RestApiSuccess;
import com.multiplayerwordle.pojo.UserDetails;
import com.multiplayerwordle.service.SignUpService;

@RestController
@RequestMapping(value = "/api/signup")
@CrossOrigin(origins = "https://multiplayerwordle.herokuapp.com/")
public class SignUpController {

	@Autowired
	SignUpService addUserDetails;
	
	
	
	@PostMapping(value="create-user")
	public ResponseEntity<Object> addUser(@RequestBody UserDetails user) throws MessagingException {
		user = addUserDetails.addUser(user);
		RestApiSuccess result = new RestApiSuccess(user);
		return new ResponseEntity<>(result, result.getStatus());
		
	}
	
	
}
