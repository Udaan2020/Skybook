package com.busnama.userService.testController;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.skybook.userService.controller.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@Autowired
	private UserController userController;

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
	public void registrationControllerTest() throws Exception {
		
	}
}
