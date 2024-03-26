package com.springboot.RailwayTicket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.RailwayTicket.authentication.AuthenticationRequest;
import com.springboot.RailwayTicket.authentication.AuthenticationResponse;
import com.springboot.RailwayTicket.dao.UserDao;
import com.springboot.RailwayTicket.entity.User;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	public UserDao userDao;
	
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenService jwtTokenService;
	
	@Override
	public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
		System.out.println("!!!!!!!!!!!!!!");
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		
		System.out.println("@@@@@@@@@@@@@@@@");
		
		User user = userDao.findByUserName(authenticationRequest.getUsername())
				.orElseThrow(() -> new IllegalArgumentException("User details ot found"));
		
		System.out.println(user.toString()); 
		
		
		String  jwtToken = jwtTokenService.generateToken(user);
		//String refreshToken  =  jwtTokenService.refreshToken(new HashMap<>(), user);
		
		
		return AuthenticationResponse.builder().Token(jwtToken).build();
	}
}
