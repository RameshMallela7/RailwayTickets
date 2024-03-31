package com.springboot.RailwayTicket.entity;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Train")
public class Train {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "trainId")
	public Integer trainId; //: Unique identifier for the train.
	
	@Column(name = "trainNumber")
	public String trainNumber; //: Train number (e.g., 12345).
	
	@Column(name = "trainName")
	public String trainName; //: Name of the train.
	
	@Column(name = "sourceStation")
	public String sourceStation; //: Source station for the train journey.
	
	@Column(name = "destinationStation")
	public String destinationStation; //: Destination station for the train journey.
	
	@Column(name = "departureTime")
	public LocalTime departureTime; //: Departure time of the train.
	
	@Column(name = "arrivalTime")
	public LocalTime arrivalTime; //: Arrival time of the train.
	
	@Column(name = "availableSeats")
	public Integer availableSeats; //: Number of available seats on the train.
	
	/*@Column(name = "dayInWeek")
	private String dayInWeek;  */

}
