package com.multiplayerwordle.pojo;

import org.springframework.http.HttpStatus;

public class RestApiSuccess {

	private HttpStatus status;
	private String message;
	private Object body;
	
	public RestApiSuccess() {
		super();
		this.status = HttpStatus.OK;
		this.message = "Success";
	}
	
	
	public RestApiSuccess(Object body) {
		this();
		this.body = body;
		
	}
	
	public RestApiSuccess(String message) {
		this();
		this.message = message;
	}
	
	public RestApiSuccess(String message, Object body) {
		this();
		this.message = message;
		this.body = body;
	}
	
	public RestApiSuccess(HttpStatus status) {
		this();
		this.status = status;
	}
	
	
	public RestApiSuccess(HttpStatus status, String message) {
		this();
		this.status = status;
		this.message = message;
	}


	public HttpStatus getStatus() {
		return status;
	}


	public void setStatus(HttpStatus status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Object getBody() {
		return body;
	}


	public void setBody(Object body) {
		this.body = body;
	}
	
	
}

