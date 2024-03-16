package com.springboot.RailwayTicket.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.RailwayTicket.model.UserModel;

@Component
public interface UserService {

	public UserModel createUser(UserModel userModel);

	public List<UserModel> getUserDetails();
}
