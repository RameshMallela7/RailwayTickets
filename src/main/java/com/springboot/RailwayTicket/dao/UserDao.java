package com.springboot.RailwayTicket.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.RailwayTicket.entity.UserDetails;

@Repository
public interface UserDao extends JpaRepository<UserDetails, Integer>{
	
}
