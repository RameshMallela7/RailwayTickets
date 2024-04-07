package com.springboot.RailwayTicket.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.springboot.RailwayTicket.dao.UserProfileDao;
import com.springboot.RailwayTicket.entity.UserProfile;
import com.springboot.RailwayTicket.service.JwtTokenService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
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
		log.info("RailwayTicketUtils - getUserProfileDetails " + token);
		
		String  userName = jwtTokenService.extractUserName(token);
		log.info("RailwayTicketUtils - userName " + userName);
		
		return userProfileDao.findByUserName(userName)
		.orElseThrow();
		
	}
	
	public String getToken() {
		String authHeader = request.getHeader(authorization);
		return authHeader.substring(7);
	}

}
