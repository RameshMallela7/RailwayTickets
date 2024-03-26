package com.springboot.RailwayTicket.service;

import org.springframework.stereotype.Component;

import com.springboot.RailwayTicket.authentication.AuthenticationRequest;
import com.springboot.RailwayTicket.authentication.AuthenticationResponse;

@Component
public interface AuthenticationService {

	AuthenticationResponse login(AuthenticationRequest authenticationRequest);

}
