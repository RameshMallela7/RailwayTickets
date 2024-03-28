package com.springboot.RailwayTicket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.RailwayTicket.dao.UserDao;
import com.springboot.RailwayTicket.entity.User;

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
