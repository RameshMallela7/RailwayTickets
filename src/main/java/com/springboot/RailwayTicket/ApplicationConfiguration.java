package com.springboot.RailwayTicket;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.RailwayTicket.dao.UserDao;
import com.springboot.RailwayTicket.excption.UserNotFountException;

@Configuration
public class ApplicationConfiguration {

	@Autowired
	public UserDao userDao;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return userName -> userDao.findByUserName(userName)
				.orElseThrow(() -> new UserNotFountException("User name not found"));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//Responsible for getting username and password from DB
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	 @Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	 	return config.getAuthenticationManager();
	 }
}