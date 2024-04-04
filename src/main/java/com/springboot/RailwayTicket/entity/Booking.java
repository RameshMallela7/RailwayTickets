package com.springboot.RailwayTicket.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@ToString(exclude = "ticket")
@Builder
@Entity
@Table(name = "Booking") //Parent Table
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "booking_gen")
	@SequenceGenerator(name = "booking_gen", sequenceName = "booking_seq",
						initialValue = 1, allocationSize = 1)
	@Column(name = "bookingId")
	public Long bookingId;
	
	//Owner
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_userId")
	public UserProfile userProfile;
	
	//@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	//@JoinColumn(name = "fk_trainId")
	//private Train train;
	
	@Column(name= "bookingDate") 
	public LocalDate bookingDate;
	
	// ********** Each booking can have one tickets, ******************
	//**** BI-DIRECTIONAL  relationship ****
	//Owner
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_ticketId")
	public Ticket ticket;

	
	

}
