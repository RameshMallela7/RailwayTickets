package com.springboot.RailwayTicket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorResponse {

	private String error;
	private String message;
	private int statusCode;
}
