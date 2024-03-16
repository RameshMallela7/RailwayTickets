package com.springboot.RailwayTicket.service;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.RailwayTicket.dao.UserDao;
import com.springboot.RailwayTicket.entity.User;
import com.springboot.RailwayTicket.excption.UserExcption;
import com.springboot.RailwayTicket.model.UserModel;

public class UserServiceImpl implements UserService {
	
	@Autowired
	public UserDao userDao;
	
	@Autowired
	public ModelMapper modelMapper;
	

	@Override
	public void createUser(UserModel userModel) {
		try {
			User user = modelMapper.map(userModel, User.class);
			
			userDao.save(user);
			
			if(!StringUtils.equals(userModel.getUserName(), user.getUserName())) {
				throw new UserExcption("Expction while inserting data in database");
			}
			
		}catch (Exception e) {
			throw new UserExcption("Model mapper excetion");
		}
	}

}
