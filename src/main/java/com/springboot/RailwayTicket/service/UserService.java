package com.springboot.RailwayTicket.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.springboot.RailwayTicket.authentication.AuthenticationResponse;
import com.springboot.RailwayTicket.authentication.RegisterRequest;
import com.springboot.RailwayTicket.entity.User;



@Component
public interface UserService {

	public AuthenticationResponse createUser(RegisterRequest registerRequest);

	public List<User> getUserDetails();

	public UserDetails getUserById(int id);

	//UserDetailsService userDetailsService();

}
