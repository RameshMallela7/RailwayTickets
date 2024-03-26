package com.springboot.RailwayTicket.model;

import com.springboot.RailwayTicket.utils.RolesEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


public class UserProfileModelDummy {
	
	private Long userId;
	private String fristName;
	private String lastName;
	private RolesEnum roles;
	private String email;
	private String dateOfBirth;
	private String phoneNumber;
	private String address;

}
