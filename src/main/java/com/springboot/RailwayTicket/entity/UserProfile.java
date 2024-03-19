package com.springboot.RailwayTicket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "userprofile")
@Data
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private Long userId;
	
	@OneToOne
	@PrimaryKeyJoinColumn(name= "userId")
	private User user;
	
	@Column(name = "firstName")
	private String fristName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "dateOfBirth")
	private String dateOfBirth;
	
	@Column(name = "phoneNumber")
	private String phoneNumber;
	
	@Column(name = "address")
	private String address;
}
