package com.springboot.RailwayTicket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "Users")
@Data
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int userId;
	@Column(name = "userName")
	private String userName;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;
	@Column(name = "firstName")
	private String fristName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "dateOfBirth")
	private String dateOfBirth;
	@Column(name = "phoneNumber")
	private String phoneNumber;
	@Column(name = "address")
	private String address;

}