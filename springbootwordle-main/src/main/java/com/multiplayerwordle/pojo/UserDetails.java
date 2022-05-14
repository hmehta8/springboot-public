package com.multiplayerwordle.pojo;

public class UserDetails {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String repeatPassword;
	private boolean firstNameValid;
	private boolean lastNameValid;
	private boolean emailValid;
	private boolean passwordValid;
	private boolean repeatPasswordValid;
	private boolean userExits;
	
	public UserDetails() {
		
	}

	
	public UserDetails(String firstName, String lastName, String email, String password, String repeatPassword,
			boolean firstNameValid, boolean lastNameValid, boolean emailValid, boolean passwordValid,
			boolean repeatPasswordValid) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.repeatPassword = repeatPassword;
		this.firstNameValid = firstNameValid;
		this.lastNameValid = lastNameValid;
		this.emailValid = emailValid;
		this.passwordValid = passwordValid;
		this.repeatPasswordValid = repeatPasswordValid;
	}


	
	public boolean isUserExits() {
		return userExits;
	}


	public void setUserExits(boolean userExits) {
		this.userExits = userExits;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRepeatPassword() {
		return repeatPassword;
	}


	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}


	public boolean isFirstNameValid() {
		return firstNameValid;
	}


	public void setFirstNameValid(boolean firstNameValid) {
		this.firstNameValid = firstNameValid;
	}


	public boolean isLastNameValid() {
		return lastNameValid;
	}


	public void setLastNameValid(boolean lastNameValid) {
		this.lastNameValid = lastNameValid;
	}


	public boolean isEmailValid() {
		return emailValid;
	}


	public void setEmailValid(boolean emailValid) {
		this.emailValid = emailValid;
	}


	public boolean isPasswordValid() {
		return passwordValid;
	}


	public void setPasswordValid(boolean passwordValid) {
		this.passwordValid = passwordValid;
	}


	public boolean isRepeatPasswordValid() {
		return repeatPasswordValid;
	}


	public void setRepeatPasswordValid(boolean repeatPasswordValid) {
		this.repeatPasswordValid = repeatPasswordValid;
	}


	
	
}
