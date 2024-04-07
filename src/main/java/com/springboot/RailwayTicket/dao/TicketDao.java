package com.springboot.RailwayTicket.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.RailwayTicket.entity.Passenger;
import com.springboot.RailwayTicket.entity.Ticket;
import com.springboot.RailwayTicket.entity.Train;

@Repository
public interface TicketDao extends JpaRepository<Ticket, Integer> {

	public Ticket findByTrain(Train train);
	
	//Derived query
	@Query("SELECT t.passengers from Ticket t where t.train = :train")
	public List<Passenger> findPassengersByTrain(@Param("train") Train train);
}
