package com.springboot.RailwayTicket.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.RailwayTicket.authentication.AuthenticationResponse;
import com.springboot.RailwayTicket.authentication.RegisterRequest;
import com.springboot.RailwayTicket.dao.UserDao;
import com.springboot.RailwayTicket.entity.User;
import com.springboot.RailwayTicket.excption.UserExcption;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	public UserDao userDao;
	
	@Autowired
	public PasswordEncoder encoder;
	
	@Autowired
	public JwtTokenService jwtTokenService;

	
	@Override
	public AuthenticationResponse createUser(RegisterRequest registerRequest) {
		System.out.println("get data to service " + registerRequest.toString());
		try {
			User user = User.builder()
			.userName(registerRequest.getUserName())
			.password(encoder.encode(registerRequest.getPassword()))
			.email(registerRequest.getEmail())
			.firstName(registerRequest.getFristName())
			.lastName(registerRequest.getLastName())
			.dateOfBirth(registerRequest.getDateOfBirth())
			.phoneNumber(registerRequest.getPhoneNumber())
			.address(registerRequest.getAddress())
			.role(registerRequest.getRole())
			.build();
			
			User uer =  userDao.save(user);
			
			System.out.println(uer.toString());
			
			if(StringUtils.equals(user.getUsername(), registerRequest.getUserName())){
				String token = jwtTokenService.generateToken(new HashMap<>(),user);
				
				System.out.println(token);
				return AuthenticationResponse.builder()
						.Token(token)
						.build();
			}
			throw new Exception("Exception while inserting data in DB");
			
		}catch (Exception e) {
			throw new UserExcption(e.getMessage());
		}
	}


	@Override
	public List<User> getUserDetails() {
		return userDao.findAll();
		
		/*return userDetailsListResponse.stream()
				.map(userResponse -> modelMapper.map(userResponse, UserDetailsModel.class))
				.collect(Collectors.toList());*/
		
		
	}


	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.findById(id).get();
		
	}
	
	/*@Override
	public UserDetailsService userDetailsService() {
		
		return userName -> userDao.findByUserName(userName).orElseThrow(()->new  UserNotFountException("User name not found"));
		
		return new UserDetailsS ervice() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return userDao.findByUserName(username).orElseThrow(() -> new UserNotFountException("User Name not found"));
			}
		};
	}*/
	
	

}
