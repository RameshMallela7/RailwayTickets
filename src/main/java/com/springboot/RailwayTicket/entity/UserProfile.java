package com.springboot.RailwayTicket.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "ticket") // Exclude the 'booking' field from toString()
@Builder
@Entity
@Table(name = "UserProfile")
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long userId;
	
	@Column(name = "userName", 
			nullable = false, 
			unique = true, 
			updatable = false)
	private String userName;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "dateOfBirth")
	private LocalDate dateOfBirth;
	
	@Column(name = "phoneNumber",
			unique = true)
	private String phoneNumber;
	
	@Column(name = "address")
	private String address;
	
	
	//refference type
	//Bi-directional relationship
	//@OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL)
	//private Ticket ticket;
	
	//refference type
	//Bi-directional relationship
	//@OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL)
	//private Ticket ticket;
	
	/*@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@JoinColumn(name = "fk_bookingId", nullable = false, insertable = false, updatable = false)
	@JsonIgnoreProperties
	private List<Booking> bookings; */
	
}
