package com.springboot.RailwayTicket.model;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TrainModel {

	public Integer trainId; 
	public String trainNumber; 
	public String trainName; 
	public String sourceStation; 
	public String destinationStation; 
	public LocalTime departureTime; 
	public LocalTime arrivalTime; 
	public Integer availableSeats; 

}
