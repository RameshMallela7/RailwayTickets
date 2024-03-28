package com.springboot.RailwayTicket.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity(name = "Ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "ticketId")
	public int ticketId; //; //: Unique identifier for the ticket.
	
	//@OneToOne()
	public int trainId; //; //: Identifier for the train associated with the ticket.
	
	//@OneToOne()
	public int userId; //; //: Identifier for the user who booked the ticket.
	
	public String sourceStation; //: Source station for the journey.
	
	public String destinationStation; //: Destination station for the journey.
	
	public Date departureTime; //: Departure time of the train.
	
	public String arrivalTime; //: Arrival time of the train.
	
	public Date bookingDate; //: Date when the ticket was booked.
	
	public String seatNumber ;


}
