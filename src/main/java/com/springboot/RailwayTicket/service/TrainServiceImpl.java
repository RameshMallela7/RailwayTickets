package com.springboot.RailwayTicket.service;

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

	@Override
	public void addNewTrainDetails(TrainModel trainModel) {
		
		trainDao.save(modelMapper.map(trainModel, Train.class));
	}

}
