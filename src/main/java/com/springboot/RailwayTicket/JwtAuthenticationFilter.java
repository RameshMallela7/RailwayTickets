package com.springboot.RailwayTicket;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springboot.RailwayTicket.authentication.AuthenticationResponse;
import com.springboot.RailwayTicket.service.JwtTokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * When ever request came to controller, this will act as filter between it, its a gateway
 * JwtAuthenticationFilter is first layer comes to the frame
 * JWT Validation  --> JWT service
 * last one SecurityConfig layer 
 * Dispatcher Servlet
 * controller layer
 * 
 * This layer will check JWT Token is present are not
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter{  // OncePerRequestFilter is provided by spring, internally it implements Filer 
	
	@Value("${jwt.header}")
	private String authorization;
	
	@Autowired
	private JwtTokenService jwtTokenService;
	
	@Autowired
	private UserDetailsService userService;
	
	@Autowired
	private AuthenticationResponse authenticationResponse;

	@Override
	protected void doFilterInternal(
			@NonNull HttpServletRequest request, 
			@NonNull HttpServletResponse response, 
			@NonNull FilterChain filterChain)
			throws ServletException, IOException {

		final String authHeader = request.getHeader(authorization);
		final String jwt;
		final String userName;
		
		
		log.info("JwtAuthenticationFilter -->authHeader   -> "+ authHeader);
		
		if(StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		
		jwt = authHeader.substring(7);
		log.info("JwtAuthenticationFilter -->jwt -->  " + jwt);
		authenticationResponse.setToken(jwt);
		
		userName = jwtTokenService.extractUserName(jwt);
		log.info("JwtAuthenticationFilter -->userName -->  " + userName);
		
		                                       //Check user is already authenticated 
		if(StringUtils.isNotEmpty(userName) && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userService.loadUserByUsername(userName);
			
			log.info("JwtAuthenticationFilter -->userDetails -->  " + userDetails.toString());
			
			if(jwtTokenService.isTokenValid(jwt, userDetails)) {
				log.info("JwtAuthenticationFilter -->isTokenValid");
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
				log.info("12345");
			}
		}
		
		filterChain.doFilter(request, response);
		
	}

}
