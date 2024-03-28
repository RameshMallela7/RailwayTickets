package com.springboot.RailwayTicket.service;

import org.springframework.stereotype.Component;

import com.springboot.RailwayTicket.model.TrainModel;

@Component
public interface TrainService {
	
	public void addNewTrainDetails(TrainModel trainModel);

}
