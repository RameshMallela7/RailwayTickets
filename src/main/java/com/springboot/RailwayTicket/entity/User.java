package com.springboot.RailwayTicket.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.RailwayTicket.utils.RolesEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "User")
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5602011521100435243L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "user_gen")
	@SequenceGenerator(name = "user_gen", sequenceName = "user_seq",
						initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false)
	private Long userId;
	
	@Column(name = "userName", 
			nullable = false, 
			unique = true, 
			updatable = false)
	private String userName;
	
	@Column(name = "password", 
			nullable = false, 
			updatable = false)
	private String password;
	
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
	
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	//@Enumerated(EnumType.ORDINAL) in DB it will store 0 (ADMIN),1(USER)
	private RolesEnum role;
	
	
	
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	//@JoinColumn(name = "bookingId", nullable = false, insertable = false, updatable = false)
	//private List<Booking> bookings;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}
	@Override
	public String getUsername() {
		return userName;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	


}