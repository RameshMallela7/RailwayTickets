package com.springboot.RailwayTicket.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.RailwayTicket.dao.UserDao;
import com.springboot.RailwayTicket.entity.User;
import com.springboot.RailwayTicket.excption.UserExcption;
import com.springboot.RailwayTicket.model.UserModel;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserDao userDao;
	
	@Autowired
	public ModelMapper modelMapper;
	

	@Override
	public UserModel createUser(UserModel userModel) {
		// TODO Auto-generated method stub
		
		System.out.println("get data to service " + userModel.toString());
		
		try {
			User user = modelMapper.map(userModel, User.class);
			
			User userResponse = userDao.save(user);
			
			if(!StringUtils.equals(userModel.getUserName(), user.getUserName())) {
				throw new UserExcption("Expction while inserting data in database");
			}
			
			UserModel userModelResponse = modelMapper.map(userResponse, UserModel.class);
			
			return userModelResponse;
			
		}catch (Exception e) {
			throw new UserExcption("Model mapper excetion");
		}
	}


	@Override
	public List<UserModel> getUserDetails() {
		// TODO Auto-generated method stub
		List<UserModel> userModelResponseList = new ArrayList();
		List<User>  userListResponse = userDao.findAll();
		
		for(User userResponse: userListResponse) {

			UserModel userModelResponse = modelMapper.map(userResponse, UserModel.class);
			userModelResponseList.add(userModelResponse);
		}
		return userModelResponseList;
		
		
	}
	
	

}
