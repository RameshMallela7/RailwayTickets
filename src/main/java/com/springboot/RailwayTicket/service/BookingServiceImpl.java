package com.springboot.RailwayTicket.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.RailwayTicket.dao.BookingDao;
import com.springboot.RailwayTicket.dao.TicketDao;
import com.springboot.RailwayTicket.dao.TrainDao;
import com.springboot.RailwayTicket.entity.Booking;
import com.springboot.RailwayTicket.entity.Passenger;
import com.springboot.RailwayTicket.entity.Ticket;
import com.springboot.RailwayTicket.entity.Train;
import com.springboot.RailwayTicket.entity.UserProfile;
import com.springboot.RailwayTicket.excption.RailwayException;
import com.springboot.RailwayTicket.model.BookingModel;
import com.springboot.RailwayTicket.model.BookingRequestModel;
import com.springboot.RailwayTicket.model.TrainModel;
import com.springboot.RailwayTicket.utils.RailwayTicketUtils;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private TrainDao trainDao;
	
	@Autowired
	private TicketDao ticketDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private RailwayTicketUtils railwayTicketUtils;
	

	@Override
	public List<TrainModel> fetchTrains(TrainModel trainRequest) throws Exception {
		try {
			Optional<List<Train>> trainsList = trainDao.findBySourceStationInAndDestinationStationIn(
					Collections.singletonList(trainRequest.getSourceStation()),
					Collections.singletonList(trainRequest.getDestinationStation()));
			
			if(!trainsList.isPresent() || trainsList.get().isEmpty()) {
				throw new RailwayException("We don't have any trains from "+ trainRequest.getSourceStation()+" to "+trainRequest.getDestinationStation(),
						HttpStatus.NOT_FOUND.value());
			}
			
			trainsList.get().forEach(list -> log.info(list.toString()));
			
			return trainsList.get().stream()
					.map(train -> mapper.map(train, TrainModel.class)).toList();
			
		}catch (RailwayException e) {
		     throw new RailwayException(e.getMessage(), e.getStatusCode());
		}catch (Exception ex) {
			throw new Exception("Error occurred during fetchTrains: " + ex.getMessage());
		}
		
	}

	@Override
	public BookingModel doBooking(BookingRequestModel bookingRequestModel) throws Exception {
		log.info("doBooking_1 --  bookingRequestModel.toString() " + bookingRequestModel.toString());
		
		try {
			UserProfile  currentUserProfile = railwayTicketUtils.getUserProfileDetails();
			log.info("doBooking_1  currentUserProfile  ->"+ currentUserProfile);
			
			List<Passenger> passengerList = setSeatNumberMethod(
					bookingRequestModel.getTrain(),
					bookingRequestModel.getPassenger());
			
			passengerList.forEach(n ->log.info("doBooking_1 - passengerList > "+n));
			
			Train trainGetById= trainDao.findById(bookingRequestModel.getTrain().getTrainId()).get();
			
			
			Ticket ticket = Ticket.builder()
					.train(trainGetById)
					.passengers(passengerList)
					.build(); 
			
			Booking bookingDetails = Booking.builder()
					.userProfile(currentUserProfile)
					.bookingDate(bookingRequestModel.getBookingDate())
					.ticket(ticket)
					.build();
			
			//This *save* will insert Passengers, Ticket & Booking also
			Booking bookingResponse = bookingDao.save(bookingDetails);
			
			log.info("doBooking_1 - bookingResponse -> "+bookingResponse.toString());
			
			updateSeatNumberinTrain(trainGetById, passengerList.size());
			
			return BookingModel.builder()
					.bookingId(bookingResponse.getBookingId())
					.userProfile(bookingResponse.getUserProfile()	)
					.ticket(bookingResponse.getTicket())
					.bookingDate(bookingResponse.getBookingDate())
					.status("Completed")
					.build();
			
		}catch (RailwayException e) {
		     throw new RailwayException(e.getMessage(), e.getStatusCode());
		}catch (Exception ex) {
			throw new Exception("Error occurred during booking: " + ex.getMessage());
		}
	}

	
	private void updateSeatNumberinTrain(Train trainGetById, int noOfSeats) {
		trainGetById.setAvailableSeats(trainGetById.getAvailableSeats() - noOfSeats);
		trainDao.save(trainGetById);
	}

	
	private List<Passenger> setSeatNumberMethod(
			Train train, 
			List<Passenger> passengers) throws Exception {

		//get passengers list with train details if any ticket is booked for that train previously
		List<Passenger> resultPass =  ticketDao.findPassengersByTrain(train);
		
		// UI can take care of this validation
		if(train.getAvailableSeats() <= passengers.size()) {
			throw new Exception("Seats not available");
		}
					
		
		log.info("BookingServiceImpl - setSeatNumberMethod  - resultPass > ");
		resultPass.forEach(res -> log.info(res.toString()));
		
		//Incrementing seat numbers on basses of number of passengers came as input
		/*
		  already that train has 3 passengers > ticketDao.findPassengersByTrain
		  new passengers which came as input to this method which will be resulted with 4,5,6,..
		
		  IntStream.range(0, passengers.size()) => no. of iterations
		 */
		
	return	IntStream.range(0, passengers.size()).mapToObj(index -> {
				Passenger pass = passengers.get(index);
				Integer maxSeatNumber = 1;
				
				if(!resultPass.isEmpty()) {
					// will get maxSeatNumber from resultPass -> 3
					maxSeatNumber = resultPass.stream().max(Comparator.comparing(Passenger::getSeatNumber)).get().getSeatNumber();
					maxSeatNumber++;
				}
				
				pass.setSeatNumber(maxSeatNumber+index);
				return pass;
			}).toList();
	}
}
