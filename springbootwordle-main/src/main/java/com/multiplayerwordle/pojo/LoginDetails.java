package com.multiplayerwordle.pojo;

public class LoginDetails {

	private String loginEmail;
	private boolean loginEmailValid;
	private boolean loginPasswordValid;
	private boolean loginSuccess;
	private String loginPassword;
	private String securityCode;

	
	
	public LoginDetails() {
	
	}


	public LoginDetails(String loginEmail, boolean loginEmailValid, boolean loginPasswordValid, String loginPassword) {
		this.loginEmail = loginEmail;
		this.loginEmailValid = loginEmailValid;
		this.loginPasswordValid = loginPasswordValid;
		this.loginPassword = loginPassword;
	}

	

	
	public String getSecurityCode() {
		return securityCode;
	}


	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}


	public boolean isLoginSuccess() {
		return loginSuccess;
	}


	public void setLoginSuccess(boolean loginSuccess) {
		this.loginSuccess = loginSuccess;
	}



	public String getLoginEmail() {
		return loginEmail;
	}


	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}


	public boolean getLoginEmailValid() {
		return loginEmailValid;
	}


	public void setLoginEmailValid(boolean loginEmailValid) {
		this.loginEmailValid = loginEmailValid;
	}


	public boolean getLoginPasswordValid() {
		return loginPasswordValid;
	}


	public void setLoginPasswordValid(boolean loginPasswordValid) {
		this.loginPasswordValid = loginPasswordValid;
	}


	public String getLoginPassword() {
		return loginPassword;
	}


	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	
	
}
