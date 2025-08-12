package com.stackroute.userfavoriteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserBookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserBookServiceApplication.class, args);
	}

}
