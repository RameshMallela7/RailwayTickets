package com.springboot.RailwayTicket.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.RailwayTicket.model.BookingModel;
import com.springboot.RailwayTicket.model.BookingRequestModel;
import com.springboot.RailwayTicket.model.TrainModel;

@Component
public interface BookingService {
	
	public List<TrainModel> fetchTrains(TrainModel trainRequest) throws Exception;

	public BookingModel doBooking(BookingRequestModel bookingRequestModel) throws Exception;
	
	
}
