package com.capgemini.udaan.skybookserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SkybookServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkybookServerApplication.class, args);
	}

}
