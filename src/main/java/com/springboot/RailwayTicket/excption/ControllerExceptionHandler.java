package com.springboot.RailwayTicket.excption;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(RailwayException.class)
	public ResponseEntity<ProblemDetail> handleRailwayException(RailwayException exception){
		ErrorResponse error = ErrorResponse.create(exception,HttpStatusCode.valueOf(exception.getStatusCode()), exception.getMessage());
		return new ResponseEntity<>(error.getBody(), HttpStatusCode.valueOf(exception.getStatusCode()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ProblemDetail> handleException(Exception exception){
		ErrorResponse error = ErrorResponse.create(exception,HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), exception.getMessage());
		return new ResponseEntity<>(error.getBody(), HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
	}

}	
