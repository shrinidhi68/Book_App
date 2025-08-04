package com.stackroute.userauthenticationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableEurekaClient
@CrossOrigin(origins="http://localhost:4200")
public class UserAuthenticationServiceApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(UserAuthenticationServiceApplication.class, args);
		
	}
	

}
