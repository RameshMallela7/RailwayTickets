package com.springboot.RailwayTicket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.RailwayTicket.model.TrainModel;
import com.springboot.RailwayTicket.service.TrainService;

@RestController
@CrossOrigin
@RequestMapping("/api/admin")
public class RailwayAdminController{
	
	@Autowired
	private TrainService trainService;

	@GetMapping("/login/getAdminProfile")
	//@PreAuthorize(value = "hasAuthority('ADMIN')")
	public String getAdminById() {
		return "ADMIN --> /login/getAdminProfile";
	}
	
	
	@PostMapping("/addNewTrain")   
	public ResponseEntity<String> addNewTrain(@RequestBody TrainModel trainModel){
		System.out.println(trainModel.toString());
		trainService.addNewTrainDetails(trainModel);
		return ResponseEntity.ok("Train details added");
	}
	 
	
} 
