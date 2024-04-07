package com.springboot.RailwayTicket.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.RailwayTicket.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{

	//@Query("SELECT u.userName, u.password FROM UserDetail u WHERE u.userName = ?1")
	Optional<User> findByUserName(String username);
	
	
}
	