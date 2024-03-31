package com.springboot.RailwayTicket.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.RailwayTicket.dao.TrainDao;
import com.springboot.RailwayTicket.entity.Train;
import com.springboot.RailwayTicket.model.TrainModel;

import jakarta.transaction.Transactional;

@Service
@Transactional 
public class TrainServiceImpl implements TrainService {
	
	@Autowired
	private TrainDao trainDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Override
	public void addNewTrainDetails(TrainModel trainModel) {
		
		Train train = modelMapper.map(trainModel, Train.class);
		//train.setDepartureTime(train.getDepartureTime().);
		trainDao.save(train);
	}
	
	
	@Override
	public void addAllNewTrainDetails(List<TrainModel> listOfTrains) {
		trainDao.saveAll(listOfTrains.stream().map(train -> modelMapper.map(train, Train.class)).collect(Collectors.toList()));
	}
	

}
