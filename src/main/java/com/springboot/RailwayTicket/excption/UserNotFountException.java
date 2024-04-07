package com.springboot.RailwayTicket.excption;

public class UserNotFountException extends RuntimeException {

	private static final long serialVersionUID = 4314974326314881685L;

	public UserNotFountException(String string) {
		super(string);
	}

}
