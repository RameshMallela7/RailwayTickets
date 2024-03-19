package com.springboot.RailwayTicket.model;

import java.util.Date;

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
	public Date departureTime; //: Departure time of the train.
	public String arrivalTime; //: Arrival time of the train.
	public Date bookingDate; //: Date when the ticket was booked.
	public String seatNumber ;


}
