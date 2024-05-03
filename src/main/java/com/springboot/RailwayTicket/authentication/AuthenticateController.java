package com.springboot.RailwayTicket.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.RailwayTicket.service.AuthenticationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticateController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "This is Welcome page without auth";
	}
	
	@PostMapping("/registration")   //  Add User details
	public ResponseEntity<String> createUser(@RequestBody RegisterRequest registerRequest) throws Exception{
		
		if(authenticationService.userNameIsPresent(registerRequest.getUserName())) {
			return new ResponseEntity<>("User name already exists", HttpStatus.CONFLICT);
		}
		
		AuthenticationResponse authenticationResponse = authenticationService.createUser(registerRequest);
		return new ResponseEntity<>(authenticationResponse.toString(),HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping("/login")   
	public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationResponse){
		log.info(authenticationResponse.toString());
		return ResponseEntity.ok(authenticationService.login(authenticationResponse));
	}
	
	

	
	
}
