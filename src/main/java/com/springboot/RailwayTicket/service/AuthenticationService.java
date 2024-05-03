package com.springboot.RailwayTicket.service;

import org.springframework.stereotype.Component;

import com.springboot.RailwayTicket.authentication.AuthenticationRequest;
import com.springboot.RailwayTicket.authentication.AuthenticationResponse;
import com.springboot.RailwayTicket.authentication.RegisterRequest;

@Component
public interface AuthenticationService {

	AuthenticationResponse login(AuthenticationRequest authenticationRequest);

	AuthenticationResponse createUser(RegisterRequest registerRequest) throws Exception;

	boolean userNameIsPresent(String userName);

}
