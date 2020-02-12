package com.capgemini.uraan.skybook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SkybookApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkybookApplication.class, args);
	}

}
