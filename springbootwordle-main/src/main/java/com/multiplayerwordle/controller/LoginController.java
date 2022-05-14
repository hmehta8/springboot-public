package com.multiplayerwordle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multiplayerwordle.pojo.LoginDetails;
import com.multiplayerwordle.pojo.RestApiSuccess;
import com.multiplayerwordle.service.LoginService;

@RestController
@CrossOrigin(origins = "https://multiplayerwordle.herokuapp.com/")
@RequestMapping(value = "/api/login")
public class LoginController {
	
	@Autowired
	LoginService loginUserDetails;
	
	@GetMapping(value="deployed")
	public String welcomeMessage() {
		return "Deployed Successfully";
	}
	
	@PostMapping(value="login-user")
	public ResponseEntity<Object> addUser(@RequestBody LoginDetails user) {
		user = loginUserDetails.validateUser(user);
		RestApiSuccess result = new RestApiSuccess(user);
		return new ResponseEntity<>(result, result.getStatus());
		
	}
	
	@PostMapping(value="security-code")
	public ResponseEntity<Object> sendSecurityCode(@RequestBody LoginDetails user ) {
		loginUserDetails.sendEmailForMultifactor(user.getLoginEmail(), user.getSecurityCode());
		RestApiSuccess result = new RestApiSuccess(user);
		return new ResponseEntity<>( result.getStatus());
	}
	
	@PostMapping(value="validate-input/{word}")
	public boolean validateInput(@PathVariable("word") String word) {
		
		boolean val = loginUserDetails.validateUserInput(word);
		
		return val;
	
	}
	
	@PostMapping(value="share-id/{roomid}/{email}")
	public void shareRoomId(@PathVariable("roomid") String roomid, @PathVariable("email") String email) {
		
		loginUserDetails.shareId(roomid, email);
	}
}
