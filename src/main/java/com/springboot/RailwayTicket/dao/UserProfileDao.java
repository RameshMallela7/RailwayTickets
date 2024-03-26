package com.springboot.RailwayTicket.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.RailwayTicket.entity.UserProfile;

@Repository
public interface UserProfileDao extends JpaRepository<UserProfile, Integer>{

}
