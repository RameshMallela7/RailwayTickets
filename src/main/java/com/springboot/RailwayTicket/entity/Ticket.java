package com.springboot.RailwayTicket.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "ticketId")
	public int ticketId; //; //: Unique identifier for the ticket.
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "trainId")
	public Train train; //; //: Identifier for the train associated with the ticket.
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public UserProfile userProfile; //; //: Identifier for the user who booked the ticket.
	
	//refference type
	//Bi-directional relationship
	@OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Passenger> passengers;
	
	
	//one tickets can be done in one booking,
	//**** BI-DIRECTIONAL  relationship ****
	//Refference 
	@OneToOne(mappedBy = "ticket",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Booking booking;	
	

}
