package com.springboot.RailwayTicket.service;

import org.springframework.stereotype.Component;

import com.springboot.RailwayTicket.model.BookingModel;

@Component
public interface BookingService {
	
	public BookingModel doBooking(BookingModel bookingModel) ;

}
