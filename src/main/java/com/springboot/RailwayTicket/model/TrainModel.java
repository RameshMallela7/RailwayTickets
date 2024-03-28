package com.springboot.RailwayTicket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TrainModel {

	public int trainId; 
	public String trainNumber; 
	public String trainName; 
	public String sourceStation; 
	public String destinationStation; 
	public String departureTime; 
	public String arrivalTime; 
	public String availableSeats; 

}
