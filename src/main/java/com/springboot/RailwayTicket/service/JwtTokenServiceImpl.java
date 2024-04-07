	package com.springboot.RailwayTicket.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/*
 * To Validate token
 * To Generate token
 * To extract UserName from token
  * 
 */
@Service
@Transactional
@Slf4j
public class JwtTokenServiceImpl implements JwtTokenService {
	
	@Value("${jwt.expiration}")
	private static String expriationTokenTime;
	
	public static final String SECRET = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";

	
	//****************** generates TOKEN ****************
	@Override
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(),userDetails);

	}
	
	@Override
	public String generateToken(Map<String, Object> extraClaim, UserDetails userDetails) {
		log.info("generateToken -> "+userDetails.getUsername());
		return Jwts.builder()
				.setClaims(extraClaim)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();

	}

	private Key getSignKey() {
		byte[] byt= Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(byt);
	}
	
	
	//**************  used to extract UserName from token  *****************************
	@Override
	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	
	
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
  //**************  used to check Token validity  *****************************
    @Override
    public boolean isTokenValid(String token, UserDetails userDetails){
    	
    	final String userName = extractUserName(token);
    	log.info("JwtTokenServiceImpl  --> isTokenValid -userName  "+userName);
    	log.info("JwtTokenServiceImpl  --> isTokenValid -userDetails.getUsername()   "+userDetails.getUsername());
    	log.info("JwtTokenServiceImpl  --> isTokenValid -isTokenExpired(token) "+isTokenExpired(token));
    	return (StringUtils.equals(userName, userDetails.getUsername()) && !isTokenExpired(token));
    	
    }

	private boolean isTokenExpired(String token) {
		return extractClaim(token, Claims::getExpiration).before(new Date());
	}
}
