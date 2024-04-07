package com.springboot.RailwayTicket.model;

import java.time.LocalDate;

import com.springboot.RailwayTicket.entity.Ticket;
import com.springboot.RailwayTicket.entity.UserProfile;

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
public class BookingModel {
	
	public Long bookingId;
	public UserProfile userProfile;
	public Ticket ticket;
	public LocalDate bookingDate;
	public String status;

}
