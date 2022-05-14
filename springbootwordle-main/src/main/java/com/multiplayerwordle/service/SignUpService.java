package com.multiplayerwordle.service;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.multiplayerwordle.domain.SignUpEntity;
import com.multiplayerwordle.pojo.UserDetails;
import com.multiplayerwordle.repository.SignUpRepository;




@Service
public class SignUpService {
	
	@Autowired
	private SignUpRepository signUpRepo;
	
	@Autowired 
	private JavaMailSender mailSender;
	
	@Value("${email")
	private String fromEmail;


	public UserDetails addUser(UserDetails user) throws MessagingException {
		
		boolean validateUserFirstName = validateFirstName(user.getFirstName());
		boolean validateUserLastName = validateLastName(user.getLastName());
		boolean validateEmail = validateEmail(user.getEmail());
		boolean validatePassword = validatePassword(user.getPassword());
		boolean validateRepeatPassword = validateRepeatPassword(user.getRepeatPassword(), user.getPassword());
		
		user.setFirstNameValid(validateUserFirstName);
		user.setLastNameValid(validateUserLastName);
		user.setEmailValid(validateEmail);
		user.setPasswordValid(validatePassword);
		user.setRepeatPasswordValid(validateRepeatPassword);
		
		saveUser(user);
		
		return user;
	}
	
	public void saveUser(UserDetails user) throws MessagingException {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		if (user.isFirstNameValid() && user.isLastNameValid() && user.isEmailValid() && 
			user.isPasswordValid() && user.isRepeatPasswordValid()) {
			
			String firstName = user.getFirstName();
			String lastName = user.getLastName();
			String email = user.getEmail();
			String encryptedPassword = passwordEncoder.encode(user.getPassword());
			
			SignUpEntity signUpUser = new SignUpEntity(firstName, lastName, email, encryptedPassword);
			
			if(!checkUserExists(email)) {
				System.out.println(user);
				signUpRepo.save(signUpUser);
				user.setUserExits(false);
				user.setPassword(null);
				user.setRepeatPassword(null);
				sendEmail(email);
			} else {
				user.setUserExits(true);
				user.setPassword(null);
				user.setRepeatPassword(null);
			}
			
		}
		
	}
	
	public void sendEmail(String toEmail) {
		SimpleMailMessage mail = new SimpleMailMessage();
		
		try {
			mail.setFrom(fromEmail);
			mail.setTo(toEmail);
			mail.setText("Thanks you for registration. Now, you can login and continue playing wordle.");
			mail.setSubject("Account Created Successfully !!");
			
			mailSender.send(mail);
			
			
		}catch(Exception ex) {
			System.out.println("Email Failed");
		}
			
	}
	
	public boolean checkUserExists(String email) {
		
		List<SignUpEntity> registeredUsers = signUpRepo.findByEmail(email);
		
		if(registeredUsers.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean validateFirstName(String firstName) {
		String regex = "^[a-zA-Z]{1,30}$";
		  
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
  
        
        if (firstName == null) {
            return false;
        }
  
     
        Matcher m = p.matcher(firstName);
  
        
        return m.matches();
	}
	
	public boolean validateLastName(String lastName) {
		String regex = "^[a-zA-Z]{0,30}$";
		  
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
  
        
        Matcher m = p.matcher(lastName);
  
        
        return m.matches();
	}
	
	public boolean validateEmail(String email) {
		
		String regex = "[a-z0-9._%+-]{1,50}@[a-z0-9]{1,50}[.][a-z]{2,3}$";
		
		if(email == null) {
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
	
	
	public boolean validateRepeatPassword(String repeatPassword, String password) {
		
		if(repeatPassword == null || repeatPassword.equals("")) {
			return false;
		}
		
		if(repeatPassword.equals(password)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkForWhiteSpace(String password) {
		
		for(int i = 0; i < password.length(); i++) {
			
			if(password.charAt(i) == ' ') {
				return true;
			}
		}
		
		return false;
		
	}
	
	
}
