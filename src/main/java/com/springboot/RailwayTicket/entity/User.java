package com.springboot.RailwayTicket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
