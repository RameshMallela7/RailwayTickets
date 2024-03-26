package com.springboot.RailwayTicket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.RailwayTicket.dao.UserDao;

@SpringBootApplication
public class RailwayTicketApplication implements CommandLineRunner {

	@Autowired
	private UserDao userDao;
	
	public static void main(String[] args) {
		SpringApplication.run(RailwayTicketApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
