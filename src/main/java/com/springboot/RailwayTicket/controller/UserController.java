package com.springboot.RailwayTicket.controller;

import java.util.List;

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
@RequestMapping("/usersDetails")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getUserDetails")
	public List<UserModel> getUserDetails(){
		System.out.println("123456789");
		return userService.getUserDetails();
		//return new ResponseEntity<>("Data is comming to controller", HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping("/signup")
	public UserModel createUser(@RequestBody UserModel userModel){
		
		UserModel userModelResponse = new UserModel();
		
		System.out.println(userModel.toString());
		userModelResponse = userService.createUser(userModel);
		return userModelResponse;
		
		
	}
	
}
