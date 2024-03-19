package com.springboot.RailwayTicket.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.RailwayTicket.dao.UserDao;
import com.springboot.RailwayTicket.entity.UserDetails;
import com.springboot.RailwayTicket.excption.UserExcption;
import com.springboot.RailwayTicket.model.UserDetailsModel;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	public UserDao userDao;
	
	@Autowired
	public ModelMapper modelMapper;
	

	@Override
	public UserDetailsModel createUser(UserDetailsModel userDetailsModel) {
		// TODO Auto-generated method stub
		
		System.out.println("get data to service " + userDetailsModel.toString());
		
		try {
			UserDetails userDetails = modelMapper.map(userDetailsModel, UserDetails.class);
			UserDetails userResponse = userDao.save(userDetails);
			if(!StringUtils.equals(userDetailsModel.getUserName(), userDetails.getUserName())) {
				throw new UserExcption("Expction while inserting data in database");
			}
			UserDetailsModel UserDetailsModelResponse = modelMapper.map(userResponse, UserDetailsModel.class);
			return UserDetailsModelResponse;
			
		}catch (Exception e) {
			throw new UserExcption("Model mapper excetion");
		}
	}


	@Override
	public List<UserDetailsModel> getUserDetails() {
		// TODO Auto-generated method stub
		List<UserDetails>  userDetailsListResponse = userDao.findAll();
		
		return userDetailsListResponse.stream()
				.map(userResponse -> modelMapper.map(userResponse, UserDetailsModel.class))
				.collect(Collectors.toList());
		
		
	}


	@Override
	public UserDetailsModel getUserById(int id) {
		// TODO Auto-generated method stub
		UserDetails userResponse = userDao.findById(id).get();
		UserDetailsModel UserDetailsModelResponse = modelMapper.map(userResponse, UserDetailsModel.class);
		return UserDetailsModelResponse;
		
	}
	
	

}
