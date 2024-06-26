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
import com.springboot.RailwayTicket.mail.SendMail;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
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
	
	@Autowired
	private SendMail sendMail;
	
	@Override
	public boolean userNameIsPresent(String userName) {
		return userProfileDao.findByUserName(userName).isPresent();
	}
	
	@Override
	public AuthenticationResponse createUser(RegisterRequest registerRequest) throws Exception {
		log.info("get data to service " + registerRequest.toString());
		try {
			
			/*if(!sendMail.emailIsValid(registerRequest.getEmail())) {
				throw new UserExcption("invalid Email address provided", HttpStatus.BAD_REQUEST.value());
			}*/
			
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
			log.info(uer.toString());
			
			if(StringUtils.equals(user.getUsername(), registerRequest.getUserName())){
				
				UserProfile userProfile = modelMapper.map(registerRequest, UserProfile.class);
				userProfileDao.save(userProfile);
				
				String token = jwtTokenService.generateToken(new HashMap<>(),user);
				
				log.info(token);
				return AuthenticationResponse.builder()
						.token(token)
						.build();
			}
			
		}catch (Exception e) {
			log.info("Exception while inserting data in DB");
			throw new Exception("Exception while inserting data in DB" + e.getMessage());
		}
		return null;
	}

	
	@Override
	public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
		log.info("!!!!!!!!!!!!!!");
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		
		log.info("@@@@@@@@@@@@@@@@");
		
		User user = userDao.findByUserName(authenticationRequest.getUsername())
				.orElseThrow(() -> new IllegalArgumentException("User details ot found"));
		
		log.info(user.toString()); 
		String  jwtToken = jwtTokenService.generateToken(user);
		
		return AuthenticationResponse.builder().token(jwtToken).build();
	}
}
