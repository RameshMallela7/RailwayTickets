package com.springboot.RailwayTicket.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.RailwayTicket.dao.BookingDao;
import com.springboot.RailwayTicket.dao.PassengersDao;
import com.springboot.RailwayTicket.dao.TicketDao;
import com.springboot.RailwayTicket.dao.TrainDao;
import com.springboot.RailwayTicket.entity.Booking;
import com.springboot.RailwayTicket.entity.Passenger;
import com.springboot.RailwayTicket.entity.Ticket;
import com.springboot.RailwayTicket.entity.Train;
import com.springboot.RailwayTicket.entity.UserProfile;
import com.springboot.RailwayTicket.model.BookingModel;
import com.springboot.RailwayTicket.model.BookingRequestModel;
import com.springboot.RailwayTicket.model.TrainModel;
import com.springboot.RailwayTicket.utils.RailwayTicketUtils;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private TrainDao trainDao;
	
	@Autowired
	private TicketDao ticketDao;
	
	@Autowired
	private PassengersDao passengersDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private RailwayTicketUtils railwayTicketUtils;
	
	@Override
	public BookingModel doBooking(BookingModel bookingModel) {
		return bookingModel;
		}

	@Override
	public List<TrainModel> fetchTrains(TrainModel trainRequest) {

		List<Train> trainsList = trainDao.findBySourceStationInAndDestinationStationIn(
				Collections.singletonList(trainRequest.getSourceStation()),
				Collections.singletonList(trainRequest.getDestinationStation()))
				.get();
		
		trainsList.forEach(System.out::println);
		
		return trainsList.stream()
				.map(train -> mapper.map(train, TrainModel.class))
				.collect(Collectors.toList());
	}

	@Override
	public BookingModel doBooking_1(BookingRequestModel bookingRequestModel) throws Exception {
		System.out.println("BookingServiceImpl -- doBooking_1 --  bookingRequestModel.toString() " + bookingRequestModel.toString());

		try {
			UserProfile  currentUserProfile = railwayTicketUtils.getUserProfileDetails();
			System.out.println("BookingServiceImpl -- doBooking_1  currentUserProfile  ->"+ currentUserProfile);
			
			List<Passenger> passengerList = setSeatNumberMethod(
					bookingRequestModel.getTrain(),
					currentUserProfile,
					bookingRequestModel.getPassenger());
			passengerList.forEach(n ->{
				System.out.println("BookingServiceImpl -- doBooking_1 - passengerList > "+n);
			});
			
			
			// UI can take care of this validation
			if(bookingRequestModel.getTrain().getAvailableSeats() <= passengerList.size()) {
				throw new Exception("Seats not available");
			}
			
			Train trainGetById= trainDao.findById(bookingRequestModel.getTrain().getTrainId())
					.orElseThrow(() -> new EntityNotFoundException("Train not found with id: " +
	                        bookingRequestModel.getTrain().getTrainId()));
			
			//List<Passenger>  passLiest = passengersDao.saveAll(passengerList);
			
		
			//Fist we are saving Ticket details in DB
			Ticket ticket = Ticket.builder()
					.train(trainGetById)
				//	.userProfile(currentUserProfile)
					.passengers(passengerList)
					.build(); 
			
			Ticket tik =  ticketDao.save(ticket);
			
			/*passengerList.stream().forEach(n -> {
				//n.setTicket(ticket);
				tik.getPassengers().add(n);
				passengersDao.save(n);
			});*/
			
			
			//Prepare Booking object 
			Booking bookingDetails = Booking.builder()
					.userProfile(currentUserProfile)
					//.train(trainGetById)
					.bookingDate(bookingRequestModel.getBookingDate())
					.ticket(tik)
					.build();
			
			Booking bookingResponse = bookingDao.save(bookingDetails);
			System.out.println("BookingServiceImpl -- doBooking_1 - bookingResponse -> "+bookingResponse.toString());
			
			return BookingModel.builder()
					.bookingId(bookingResponse.getBookingId())
					.userProfile(bookingResponse.getUserProfile()	)
					.ticket(bookingResponse.getTicket())
					.bookingDate(bookingResponse.getBookingDate())
					.status("Completed")
					.build();
		}catch (EntityNotFoundException ex) {
	        ex.printStackTrace();
	        throw new Exception("Error occurred during booking: " + ex.getMessage());
	    }
		
	}

	public List<Passenger> setSeatNumberMethod(
			Train train, 
			UserProfile userprofile , 
			List<Passenger> passengers) {

		//get passengers list with train details
		List<Passenger> resultPass =  ticketDao.findPassengersByTrain(train);
		
		System.out.println("BookingServiceImpl - setSeatNumberMethod  - resultPass > ");
		resultPass.forEach(System.out::println);
		
		//Incrementing seat numbers on basses of getting list of passengers with seatnumbers
		/*
		  already that train has 3 passengers > ticketDao.findPassengersByTrain
		  new passengers which came as input to this method will be starts with 4,5,6,..
		 */
		
	return	IntStream.range(0, passengers.size()).mapToObj(index -> {
				Passenger pass = passengers.get(index);
				Integer maxSeatNumber = 1;
				
				if(resultPass.size() > 0) {
					// will get maxSeatNumber  -> 3
					maxSeatNumber = resultPass.stream().max(Comparator.comparing(Passenger::getSeatNumber)).get().getSeatNumber();
					maxSeatNumber= maxSeatNumber+1;
				}
				
				pass.setSeatNumber(maxSeatNumber+index);
				return pass;
			}).collect(Collectors.toList());
	}
}
