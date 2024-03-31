package com.springboot.RailwayTicket.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TicketModel {
	
	public int ticketId; //; //: Unique identifier for the ticket.
	public int trainId; //; //: Identifier for the train associated with the ticket.
	public int userId; //; //: Identifier for the user who booked the ticket.
	public String sourceStation; //: Source station for the journey.
	public String destinationStation; //: Destination station for the journey.
	public LocalTime departureTime; //: Departure time of the train.
	public LocalTime arrivalTime; //: Arrival time of the train.
	public LocalDate bookingDate; //: Date when the ticket was booked.


}
