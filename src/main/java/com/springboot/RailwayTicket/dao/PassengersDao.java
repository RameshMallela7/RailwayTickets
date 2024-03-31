package com.springboot.RailwayTicket.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.RailwayTicket.entity.Passenger;

@Repository
public interface PassengersDao extends JpaRepository<Passenger, Integer> {

}
