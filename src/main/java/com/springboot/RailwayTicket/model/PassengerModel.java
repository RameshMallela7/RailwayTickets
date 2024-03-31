package com.springboot.RailwayTicket.model;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.springboot.RailwayTicket.entity.Passenger;
import com.springboot.RailwayTicket.entity.Train;
import com.springboot.RailwayTicket.entity.UserProfile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PassengerModel {

	@NotEmpty
	private String name;
	 
	@Min(value = 5, message = "Age must be at least 5")
	@Max(value = 100, message = "Age must not exceed 100")
	private int age;
	
	private Integer seatNumber;
	
}
