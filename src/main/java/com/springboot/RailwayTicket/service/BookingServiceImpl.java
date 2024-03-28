package com.springboot.RailwayTicket.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.RailwayTicket.dao.BookingDao;
import com.springboot.RailwayTicket.entity.Booking;
import com.springboot.RailwayTicket.model.BookingModel;
import com.springboot.RailwayTicket.utils.RailwayTicketUtils;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private RailwayTicketUtils railwayTicketUtils;
	
	
	public BookingModel doBooking(BookingModel bookingModel) {
		System.out.println("BookingServiceImpl -- doBooking --  bookingModel.toString() " + bookingModel.toString());
		
		Booking booking = mapper.map(bookingModel, Booking.class);
		System.out.println("BookingServiceImpl -- doBooking --  booking" + booking);
		
		Booking bookingDetails = Booking.builder()
				.bookingDate(bookingModel.getBookingDate())
				.status(bookingModel.getStatus())
				.userProfile(railwayTicketUtils.getUserProfileDetails())
				.build();
		
		Booking bookingResponse = bookingDao.save(bookingDetails);
		
		System.out.println("BookingServiceImpl -- doBooking --  bookingResponse" + bookingResponse);
		return mapper.map(bookingResponse, BookingModel.class);
		
	}
}
