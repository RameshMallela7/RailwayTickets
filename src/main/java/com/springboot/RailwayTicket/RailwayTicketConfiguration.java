package com.springboot.RailwayTicket;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RailwayTicketConfiguration {
    
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
		
	}
	/*
	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}*/

}
