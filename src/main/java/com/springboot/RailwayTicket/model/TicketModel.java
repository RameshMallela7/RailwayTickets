package com.springboot.RailwayTicket.model;

import java.time.LocalDate;
import java.util.List;

import com.springboot.RailwayTicket.entity.Passenger;
import com.springboot.RailwayTicket.entity.Train;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TicketModel {
	
	public Long ticketId; //; //: Unique identifier for the ticket.
	public Train train; //; //: Identifier for the train associated with the ticket.
	public int userId; //; //: Identifier for the user who booked the ticket.
	public LocalDate bookingDate; //: Date when the ticket was booked.
	private List<Passenger> passengers;

}
