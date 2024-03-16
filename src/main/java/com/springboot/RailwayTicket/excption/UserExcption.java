package com.springboot.RailwayTicket.excption;

public class UserExcption extends RuntimeException {

	@java.io.Serial
	private static final long serialVersionUID = 7230402241512356780L;

	public UserExcption(String message , Throwable cause) {
		// TODO Auto-generated constructor stub
		super(message, cause);
	}
	
	public UserExcption(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}

}
