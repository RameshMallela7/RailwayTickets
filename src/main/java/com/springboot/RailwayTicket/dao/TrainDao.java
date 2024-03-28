package com.springboot.RailwayTicket.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.RailwayTicket.entity.Train;

@Repository
public interface TrainDao extends JpaRepository<Train, Integer> {

}
