package com.springboot.RailwayTicket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.RailwayTicket.model.BookingModel;
import com.springboot.RailwayTicket.model.BookingRequestModel;
import com.springboot.RailwayTicket.model.TrainModel;
import com.springboot.RailwayTicket.service.BookingService;

@RestController
@CrossOrigin
@RequestMapping("/api/railway")
public class RailwayController {

	@Autowired
	private BookingService bookingService;

	
	@GetMapping("/fetchTrains")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<List<TrainModel>> fetchTrains(@RequestBody TrainModel trainRequest) {
		System.out.println("Enter fetchTrains "+ trainRequest.getSourceStation() + trainRequest.getSourceStation());
		List<TrainModel> trailsList = bookingService.fetchTrains(trainRequest);
		
		return ResponseEntity.ok(trailsList);
	}
	
	
	@PostMapping("/doBooking")   
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<BookingModel> doBooking(@RequestBody BookingRequestModel bookingRequestModel) throws Exception{
		System.out.println(bookingRequestModel.toString());
		BookingModel bookingModelResponse =bookingService.doBooking_1(bookingRequestModel);
		return ResponseEntity.ok(bookingModelResponse);
	}
	
	
	
	
	
}
