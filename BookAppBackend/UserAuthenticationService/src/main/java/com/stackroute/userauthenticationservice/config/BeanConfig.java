package com.stackroute.userauthenticationservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class BeanConfig {
	
	@Bean
	public SecurityFilterChain chain(HttpSecurity http) throws Exception {
		http.csrf(C->C.disable())	
			.authorizeHttpRequests(r->r.antMatchers("**/user","/api/v1/login").permitAll().anyRequest().authenticated())
			.sessionManagement(c->c.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
	}
	

	
	
   }

