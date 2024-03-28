package com.springboot.RailwayTicket.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.RailwayTicket.entity.UserProfile;

@Repository
public interface UserProfileDao extends JpaRepository<UserProfile, Integer> {

	Optional<UserProfile> findByUserName(String username);
}
