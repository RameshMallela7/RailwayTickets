package com.springboot.RailwayTicket.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString	
@Builder
@Entity
@Table(name = "Passenger") // child table for Ticket
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "passenger_gen")
	@SequenceGenerator(name = "passenger_gen", sequenceName = "passenger_seq",
						initialValue = 1, allocationSize = 1)
	@Column(name = "passengerId")
	public Long passengerId;
	
	@NotEmpty
	@Column(name = "name")
	private String name;

	@Min(value = 5, message = "Age must be at least 5")
	@Max(value = 100, message = "Age must not exceed 100")
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "seatNumber")
	private Integer seatNumber;
	
	//Owner type
	//Many passenger can have in one ticket
	//Bi-directional relationship
	//@JoinColumn(name = "ticket_id")
	//@ManyToOne(cascade = CascadeType.ALL)
	//private Ticket ticket;
	 
	
}
