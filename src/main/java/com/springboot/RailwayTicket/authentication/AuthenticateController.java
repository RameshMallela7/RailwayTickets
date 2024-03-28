package com.springboot.RailwayTicket.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.RailwayTicket.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticateController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "This is Welcome page without auth";
	}
	
	@PostMapping("/registration")   //  Add User details
	public ResponseEntity<AuthenticationResponse> createUser(@RequestBody RegisterRequest registerRequest){
		return ResponseEntity.ok(authenticationService.createUser(registerRequest));
	}
	
	
	@PostMapping("/login")   
	public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationResponse){
		System.out.println(authenticationResponse.toString());
		return ResponseEntity.ok(authenticationService.login(authenticationResponse));
	}
	
	

	
	
}