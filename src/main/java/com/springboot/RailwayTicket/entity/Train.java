package com.springboot.RailwayTicket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Train")
public class Train {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "trainId")
	public int trainId; //: Unique identifier for the train.
	@Column(name = "trainNumber")
	public String trainNumber; //: Train number (e.g., 12345).
	@Column(name = "trainName")
	public String trainName; //: Name of the train.
	public String sourceStation; //: Source station for the train journey.
	public String destinationStation; //: Destination station for the train journey.
	public String departureTime; //: Departure time of the train.
	public String arrivalTime; //: Arrival time of the train.
	public String availableSeats; //: Number of available seats on the train.

}
