package com.springboot.RailwayTicket.excption;

public class RailwayException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 151292702309967123L;
	
	private String message;
	private int statusCode;

	public RailwayException(){
		super();
	}

	public RailwayException(String message, int statusCode){
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
