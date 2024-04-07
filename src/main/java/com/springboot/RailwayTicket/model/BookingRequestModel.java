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
public class BookingRequestModel {
	
	private Train train;
	
	private List<Passenger> passenger;
	
	public LocalDate bookingDate;

}
