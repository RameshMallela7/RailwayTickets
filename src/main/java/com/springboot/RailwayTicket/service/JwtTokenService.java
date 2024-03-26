package com.springboot.RailwayTicket.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public interface JwtTokenService {

	String generateToken(UserDetails userDetails);
	
	String extractUserName(String token);

	boolean isTokenValid(String token, UserDetails userDetails);

	String generateToken(Map<String, Object> extraClaim, UserDetails userDetails);

	


}
