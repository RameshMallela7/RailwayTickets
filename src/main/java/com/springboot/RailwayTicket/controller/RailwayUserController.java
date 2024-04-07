package com.springboot.RailwayTicket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class RailwayUserController {
	

	
	@GetMapping("/login/getuserProfile")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<String> getUser() {
		return ResponseEntity.ok("Im from secured Endpoint -- USER -> /login/getuserProfile");
	}
	

}

