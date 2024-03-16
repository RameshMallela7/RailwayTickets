package com.springboot.RailwayTicket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.RailwayTicket.model.UserModel;
import com.springboot.RailwayTicket.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/ticket")
public class UserController {
	
	public UserController() {
		// TODO Auto-generated constructor stub
		System.out.println("23456");
	}

	@Autowired
	private UserService service;		
	
	@PostMapping("/up")
	public ResponseEntity<UserModel> createUser(@RequestBody UserModel userModel){
		System.out.println("123456789");
		service.createUser(userModel);
		return new ResponseEntity<UserModel>(HttpStatus.OK);
		
		
	}
	
	@GetMapping("/sign")
	public ResponseEntity<String> getUser(){
		return new ResponseEntity<String>("Sucess",HttpStatus.OK);
		
	}
}
