package com.capgemini.skybook.bookingservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BookingServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingServicesApplication.class, args);
	}

}
