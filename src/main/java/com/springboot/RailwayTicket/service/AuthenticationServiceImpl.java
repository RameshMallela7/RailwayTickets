package com.springboot.RailwayTicket.service;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.RailwayTicket.authentication.AuthenticationRequest;
import com.springboot.RailwayTicket.authentication.AuthenticationResponse;
import com.springboot.RailwayTicket.authentication.RegisterRequest;
import com.springboot.RailwayTicket.dao.UserDao;
import com.springboot.RailwayTicket.dao.UserProfileDao;
import com.springboot.RailwayTicket.entity.User;
import com.springboot.RailwayTicket.entity.UserProfile;
import com.springboot.RailwayTicket.excption.UserExcption;

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
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserProfileDao userProfileDao;
	
	@Override
	public AuthenticationResponse createUser(RegisterRequest registerRequest) {
		System.out.println("get data to service " + registerRequest.toString());
		try {
			
			User user = User.builder()
					.userName(registerRequest.getUserName())
					.password(encoder.encode(registerRequest.getPassword()))
					.email(registerRequest.getEmail())
					.firstName(registerRequest.getFirstName())
					.lastName(registerRequest.getLastName())
					.dateOfBirth(registerRequest.getDateOfBirth())
					.phoneNumber(registerRequest.getPhoneNumber())
					.address(registerRequest.getAddress())
					.role(registerRequest.getRole())
					.build();
			
			User uer =  userDao.save(user);
			System.out.println(uer.toString());
			
			if(StringUtils.equals(user.getUsername(), registerRequest.getUserName())){
				
				UserProfile userProfile = modelMapper.map(registerRequest, UserProfile.class);
				userProfileDao.save(userProfile);
				
				String token = jwtTokenService.generateToken(new HashMap<>(),user);
				
				System.out.println(token);
				return AuthenticationResponse.builder()
						.Token(token)
						.build();
			}
			
		}catch (Exception e) {
			System.out.println("Exception while inserting data in DB");
			throw new UserExcption(e.getMessage());
		}
		return null;
	}

	
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
