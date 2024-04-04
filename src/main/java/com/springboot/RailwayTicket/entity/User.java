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
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Builder
@Entity
@Table(name = "User")
//@SecondaryTable(name = "UserProfile", pkJoinColumns = @PrimaryKeyJoinColumn(name = "userId" ))
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
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(role.name()));
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	


}