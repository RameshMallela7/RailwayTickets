package com.springboot.RailwayTicket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UserProfile")
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private Long userId;
	/*
	 * @OneToOne 
	 * @PrimaryKeyJoinColumn(name= "userId") 
	 * private UserLogin user;
	 */
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
