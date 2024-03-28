package com.springboot.RailwayTicket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileModel {

	private Long userId;
	private String userName;
	private String email;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String phoneNumber;
	private String address;
}
