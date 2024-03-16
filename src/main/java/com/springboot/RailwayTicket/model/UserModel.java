package com.springboot.RailwayTicket.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
	
	private int userId;
	private String userName;
	private String password;
	private String email;
	private String fristName;
	private String lastName;
	private Date dateOfBirth;
	private BigDecimal phoneNumber;
	private String address;

}
