package com.springboot.RailwayTicket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.RailwayTicket.utils.RolesEnum;

import lombok.RequiredArgsConstructor;
/*
 * 
 * This is binding JWTAuth and UserDetailsService(inbuilt class)
 * 
 */
@Configuration   // 
@EnableWebSecurity   // need to be together as we use Spring 3.0
@RequiredArgsConstructor
public class SecurityConfiguration {

	@Autowired
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
    public UserDetailsService userService;
	
	@Autowired
	public final AuthenticationProvider authenticationProvider;

	// While starting spring security application, it will look for securityFilterChain bean
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		/*http.csrf().disable()
        .authorizeHttpRequests()
        .requestMatchers("/api/auth/**").permitAll()
       // .requestMatchers("/h2-console/login.do?jsessionid**").permitAll()
        //.requestMatchers("/api/admin/**").hasAnyAuthority(RolesEnum.ADMIN.name())
        //.requestMatchers("/api/user/**").hasAnyAuthority(RolesEnum.USER.name())
        .anyRequest().authenticated().and()
        .sessionManagement(manage -> manage.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); */
		
		
         http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->  request.requestMatchers("/api/auth/**")
                		.permitAll()
                		.requestMatchers("/api/admin/**").hasAnyAuthority(RolesEnum.ADMIN.name())
                		.requestMatchers("/api/user/**").hasAnyAuthority(RolesEnum.USER.name())
                		.anyRequest().authenticated())
                .sessionManagement(manage -> manage.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
         System.out.println("securityFilterChain");
         
         System.out.println(authenticationProvider);
         return http.build();
    }

}