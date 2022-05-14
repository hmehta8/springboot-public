package com.multiplayerwordle.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.multiplayerwordle.domain.SignUpEntity;
import com.multiplayerwordle.pojo.LoginDetails;
import com.multiplayerwordle.repository.SignUpRepository;

@Service
public class LoginService {

	@Autowired
	private SignUpRepository signUpRepo;
	
	@Autowired 
	private JavaMailSender mailSender;
	
	@Value("${email")
	private String fromEmail;
	
	public LoginDetails validateUser(LoginDetails user) {
		
		
		boolean validateEmail = validateEmail(user.getLoginEmail());
		boolean validatePassword = validatePassword(user.getLoginPassword());
		
		user.setLoginEmailValid(validateEmail);
		user.setLoginPasswordValid(validatePassword);
		
		if(validateEmail == false || validatePassword == false) {
			return user;
		}
		
		if(validateUserCredentials(user)) {
			user.setLoginSuccess(true);
			user.setLoginPassword(null);
		}
		
		return user;
		
	}
	
	public boolean validateUserCredentials(LoginDetails user) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		List<SignUpEntity> userDetail = signUpRepo.findByEmail(user.getLoginEmail());
		
		if(userDetail.size() > 0) {
			String password = user.getLoginPassword();
			if(passwordEncoder.matches(password, userDetail.get(0).getPassword())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
						
	}
	
	public boolean validateEmail(String email) {
		String regex = "[a-z0-9._%+-]{1,50}@[a-z0-9]{1,50}[.][a-z]{2,3}$";

		if (email == null) {
			return false;
		}

		// Compile the ReGex
		Pattern p = Pattern.compile(regex);

		Matcher m = p.matcher(email);

		return m.matches();
	}
	
	
	public boolean validatePassword(String password) {
		
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
		
		if(password == null || password.equals("")) {
			return false;
		}
		
		if(checkForWhiteSpace(password)) {
			return false;
		}
		  
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
  
        
        Matcher m = p.matcher(password);
  
     
        return m.matches();
	}
	
	public boolean checkForWhiteSpace(String password) {

		for (int i = 0; i < password.length(); i++) {

			if (password.charAt(i) == ' ') {
				return true;
			}
		}

		return false;

	}
	
	public boolean validateUserInput(String word) {
		String regex = "^[a-zA-Z]{1,5}$";
		  
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
  
        
        if (word == null) {
            return false;
        }
  
     
        Matcher m = p.matcher(word);
  
        
        return m.matches();
	}
	
	public void sendEmailForMultifactor(String email, String securityCode) {
		SimpleMailMessage mail = new SimpleMailMessage();
		
		try {
			mail.setFrom(fromEmail);
			mail.setTo(email);
			mail.setText("Here is your security code: " + securityCode);
			mail.setSubject("Security Code");
			
			mailSender.send(mail);
			
			
		}catch(Exception ex) {
			System.out.println("Email Failed");
		}
	}
	
	public void shareId(String id, String email) {
		SimpleMailMessage mail = new SimpleMailMessage();
		
		try {
			mail.setFrom(fromEmail);
			mail.setTo(email);
			mail.setText("Join game with this ID: " + id);
			mail.setSubject("Game Id");
			
			mailSender.send(mail);
			
			
		}catch(Exception ex) {
			System.out.println("Email Failed");
		}
	}
	
}
