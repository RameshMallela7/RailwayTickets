package com.springboot.RailwayTicket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.RailwayTicket.model.BookingModel;
import com.springboot.RailwayTicket.service.BookingService;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class RailwayUserController {
	
	@Autowired
	private BookingService bookingService;

	
	@GetMapping("/login/getuserProfile")
	//@PreAuthorize(value = "hasAuthority('ROLE_USER')")
	public ResponseEntity<String> getUser() {
		return ResponseEntity.ok("Im from secured Endpoint -- USER -> /login/getuserProfile");
	}
	
	@PostMapping("/doBooking")   
	public ResponseEntity<BookingModel> doBooking(@RequestBody BookingModel bookingModel){
		System.out.println(bookingModel.toString());
		BookingModel bookingModelResponse =bookingService.doBooking(bookingModel);
		return ResponseEntity.ok(bookingModelResponse);
	}

}

