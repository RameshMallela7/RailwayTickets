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
public class StationModel {
	
	public Long stationId;
	public int stationCode;
	public String stationName;
	public String location;
	public String address;
	
	

}
