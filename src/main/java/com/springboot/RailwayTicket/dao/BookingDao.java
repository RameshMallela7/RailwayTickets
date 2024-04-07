package com.springboot.RailwayTicket.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.RailwayTicket.entity.Booking;

@Repository
public interface BookingDao extends JpaRepository<Booking, Integer> {

}
