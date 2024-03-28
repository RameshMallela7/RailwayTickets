package com.springboot.RailwayTicket.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.springboot.RailwayTicket.authentication.AuthenticationResponse;
import com.springboot.RailwayTicket.dao.UserProfileDao;
import com.springboot.RailwayTicket.entity.UserProfile;
import com.springboot.RailwayTicket.service.JwtTokenService;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class RailwayTicketUtils {
	
	@Value("${jwt.header}")
	private String authorization;
	
	@Autowired
	private JwtTokenService jwtTokenService;
	
	@Autowired
	private UserProfileDao userProfileDao;
	
	@Autowired
    private HttpServletRequest request;
	
	
	public  UserProfile getUserProfileDetails() {
		String token= getToken();
		System.out.println("RailwayTicketUtils - getUserProfileDetails " + token);
		
		String  userName = jwtTokenService.extractUserName(token);
		System.out.println("RailwayTicketUtils - userName " + userName);
		
		return userProfileDao.findByUserName(userName)
		.orElseThrow();
		
	}
	
	public String getToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String authHeader = request.getHeader(authorization);
		return authHeader.substring(7);
	}

}
