package com.springboot.RailwayTicket.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.RailwayTicket.entity.Passenger;
import com.springboot.RailwayTicket.entity.Train;
import com.springboot.RailwayTicket.entity.UserProfile;
import com.springboot.RailwayTicket.model.BookingModel;
import com.springboot.RailwayTicket.model.BookingRequestModel;
import com.springboot.RailwayTicket.model.TrainModel;

@Component
public interface BookingService {
	
	public BookingModel doBooking(BookingModel bookingModel) ;

	public List<TrainModel> fetchTrains(TrainModel trainRequest);

	public BookingModel doBooking_1(BookingRequestModel bookingRequestModel) throws Exception;
	
	public List<Passenger> setSeatNumberMethod(
			Train train, 
			UserProfile userprofile , 
			List<Passenger> passengers);

}
