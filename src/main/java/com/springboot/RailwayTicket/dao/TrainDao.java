package com.springboot.RailwayTicket.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.RailwayTicket.entity.Train;

@Repository
public interface TrainDao extends JpaRepository<Train, Integer> {

	Optional<List<Train>> findBySourceStationInAndDestinationStationIn(List<String> sourceStation, List<String> destinationStation);

	//findBy
}
