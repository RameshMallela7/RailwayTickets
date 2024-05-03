package com.springboot.RailwayTicket.excption;

public class UserExcption extends RuntimeException {

	private static final long serialVersionUID = 7230402241512356780L;

	private String message;
	private int statusCode;

	
	public UserExcption(String message , Throwable cause) {
		super(message, cause);
	}
	
	public UserExcption(String message) {
		super(message);
	}

	
	public UserExcption(){
		super();
	}

	public UserExcption(String message, int statusCode){
		super(message);
		this.message = message;
		this.statusCode = statusCode;
		
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
