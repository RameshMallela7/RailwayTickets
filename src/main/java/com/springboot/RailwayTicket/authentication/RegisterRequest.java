package com.springboot.RailwayTicket.authentication;

import java.time.LocalDate;

import com.springboot.RailwayTicket.utils.RolesEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class RegisterRequest {

	private String userName;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String phoneNumber;
	private String address;
	private RolesEnum role;
	
	
}
