package com.springboot.RailwayTicket.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.RailwayTicket.model.UserDetailsModel;


@Component
public interface UserService {

	public UserDetailsModel createUser(UserDetailsModel UserDetailsModel);

	public List<UserDetailsModel> getUserDetails();

	public UserDetailsModel getUserById(int id);
}
