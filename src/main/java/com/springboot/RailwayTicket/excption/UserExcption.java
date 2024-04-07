package com.springboot.RailwayTicket.excption;

public class UserExcption extends RuntimeException {

	private static final long serialVersionUID = 7230402241512356780L;

	public UserExcption(String message , Throwable cause) {
		super(message, cause);
	}
	
	public UserExcption(String message) {
		super(message);
	}

}
