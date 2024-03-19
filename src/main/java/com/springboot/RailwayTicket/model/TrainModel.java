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

	public int trainId; //: Unique identifier for the train.
	public String trainNumber; //: Train number (e.g., 12345).
	public String trainName; //: Name of the train.
	public String sourceStation; //: Source station for the train journey.
	public String destinationStation; //: Destination station for the train journey.
	public String departureTime; //: Departure time of the train.
	public String arrivalTime; //: Arrival time of the train.
	public String availableSeats; //: Number of available seats on the train.

}
