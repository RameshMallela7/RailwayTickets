package com.springboot.RailwayTicket.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.springboot.RailwayTicket.entity.User;



@Component
public interface UserService {

	public List<User> getUserDetails();

	public UserDetails getUserById(int id);

}
