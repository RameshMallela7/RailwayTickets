package com.springboot.RailwayTicket.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.RailwayTicket.model.TrainModel;

@Component
public interface TrainService {
	
	public void addNewTrainDetails(TrainModel trainModel);

	void addAllNewTrainDetails(List<TrainModel> listOfTrains);

}
