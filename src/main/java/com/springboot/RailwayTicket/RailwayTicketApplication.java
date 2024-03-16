package com.springboot.RailwayTicket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.springboot.RailwayTicket.service")
public class RailwayTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(RailwayTicketApplication.class, args);
	}

}
