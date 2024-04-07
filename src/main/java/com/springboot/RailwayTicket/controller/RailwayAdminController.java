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

import com.springboot.RailwayTicket.model.TrainModel;
import com.springboot.RailwayTicket.service.TrainService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@RequestMapping("/api/admin")
@Slf4j
public class RailwayAdminController{
	
	@Autowired
	private TrainService trainService;

	@GetMapping("/login/getAdminProfile")
	@PreAuthorize(value = "hasAuthority('ADMIN')")
	public String getAdminById() {
		return "ADMIN --> /login/getAdminProfile";
	}
	
	
	@PostMapping("/addNewTrain")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<String> addNewTrain(@RequestBody TrainModel trainModel){
		log.info(trainModel.toString());
		trainService.addNewTrainDetails(trainModel);
		return ResponseEntity.ok("Train detail added");
	}
	
	@PostMapping("/addAllNewTrains")  
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<String> addNewTrain(@RequestBody List<TrainModel> listTrainModel){
		log.info(listTrainModel.toString());
		trainService.addAllNewTrainDetails(listTrainModel);
		return ResponseEntity.ok("All Train details added");
	}
	 
	
} 
