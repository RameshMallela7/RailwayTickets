package com.springboot.RailwayTicket.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
//@Entity(name = "Station")
public class Station {
	
	public int stationId;
	public int stationCode;
	public String stationName;
	public String location;
	public String address;
	
	

}
