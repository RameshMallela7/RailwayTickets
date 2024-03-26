package com.springboot.RailwayTicket.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/admin")
public class RailwayAdminController{

	@GetMapping("/login/getAdminProfile")
	@PreAuthorize(value = "hasAuthority('ADMIN')")
	public String getAdminById() {
		return "ADMIN --> /login/getAdminProfile";
	}
	
} 
