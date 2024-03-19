package com.springboot.RailwayTicket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.RailwayTicket.model.UserDetailsModel;
import com.springboot.RailwayTicket.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/usersLogin")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getUserDetails")
	public List<UserDetailsModel> getUserDetails(){
		return userService.getUserDetails();
		
	}
	
	@GetMapping("/getMyDetails/{id}")
	public UserDetailsModel getUserById(@PathVariable int id) {
		return userService.getUserById(id);
	}
	
	@PostMapping("/signup")
	public UserDetailsModel createUser(@RequestBody UserDetailsModel userModel){
		return userService.createUser(userModel);
	}
	
	
	
}
