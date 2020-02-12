package com.busnama.userService.testService;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.skybook.userService.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@BeforeAll
	static void setUpBeforeClass() {
		

	}

	@BeforeEach
	void setup() {
		System.out.println("Test Case Started");

	}

	@AfterEach
	void tearDown() {
		System.out.println("Test Case Over");
		
	}
	
	@Test
	@DisplayName("")
	public void registrationTest() throws Exception{
		
	}
	
}
