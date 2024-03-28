package com.springboot.RailwayTicket.model;

import java.util.Date;

import com.springboot.RailwayTicket.entity.UserProfile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingModel {
	
	public int bookingId;
	public UserProfile userProfile;
	//public int ticketId;
	public Date bookingDate;
	public String status;
	
	

}
