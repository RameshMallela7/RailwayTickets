package com.springboot.RailwayTicket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "UsersDetails")
@SecondaryTable(name = "userprofile", pkJoinColumns = @PrimaryKeyJoinColumn(name = "userId" ))
@Data
public class UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long userId;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email", table = "userprofile")
	private String email;
	
	@Column(name = "firstName",  table = "userprofile")
	private String fristName;
	
	@Column(name = "lastName", table = "userprofile")
	private String lastName;
	
	@Column(name = "dateOfBirth",  table = "userprofile")
	private String dateOfBirth;
	
	@Column(name = "phoneNumber", table = "userprofile")
	private String phoneNumber;
	
	@Column(name = "address", table = "userprofile")
	private String address;

}