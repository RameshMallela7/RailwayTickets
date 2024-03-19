package com.springboot.RailwayTicket.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
//@Entity(name = "Booking")
public class Booking {
	
	public int bookingId;
	public int userId;
	public int ticketId;
	public Date bookingDate;
	public String status;
	
	

}
