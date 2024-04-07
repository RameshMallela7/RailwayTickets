package com.springboot.RailwayTicket.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springboot.RailwayTicket.entity.Passenger;
import com.springboot.RailwayTicket.entity.Train;
import com.springboot.RailwayTicket.entity.UserProfile;
import com.springboot.RailwayTicket.service.BookingServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

	@InjectMocks
	private BookingServiceImpl bookingService;
	//private TicketDao ticketDao;
	
	@Mock
	private TicketDao dao;
	
	//@Test
	public void TestBookingService() {
		Train train = Train.builder()
		.trainNumber("12985")
		.trainName("Hyderabad Express")
		.sourceStation("Hyderabad")
		.destinationStation("Chennai")
		.departureTime(LocalTime.parse("19:30"))
		.arrivalTime(LocalTime.parse("05:00"))
		.availableSeats(100).build();
		
		
		
		ArrayList<Passenger> list = new ArrayList();
		list.add(Passenger.builder().name("King").age(18).build());
		list.add(Passenger.builder().name("Queen").age(17).build());
		
		UserProfile user = UserProfile.builder()
		.userId((long) 1)
		.userName("ramesh500")
		.email("Ramesh@gmail.com")
		.firstName("Ramesh")
		.lastName("Mallea")
		.dateOfBirth(LocalDate.parse("1995-02-28")).build();
		
	}
}
