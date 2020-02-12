package com.skybook.userService.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.skybook.userService.model.User;
import com.skybook.userService.service.UserService;
import com.skybook.userService.utility.Encryptor;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	// Getter and Setter
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@ResponseBody
	@PostMapping("/registration")
	public String registration(@RequestBody Map<String, Object> requestData) {
		String customerName = requestData.get("userName").toString();
		String customerId = requestData.get("userId").toString();
		String customerMail = requestData.get("userMail").toString();
		String customerPassword = requestData.get("userPassword").toString();
		long customerNumber = Long.parseLong(requestData.get("userNumber").toString());
		// int category = Integer.parseInt(requestData.get("userCategory").toString());

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode dataResponse = objectMapper.createObjectNode();
		boolean result = false;

		try {
			customerPassword = Encryptor.encrypt(customerPassword, "Busnama");
			User customer = new User(customerName, customerId, customerMail, customerPassword, customerNumber, 2,
					false);
			result = userService.customerRegistration(customer);
			if (result == true) {
				((ObjectNode) dataResponse).put("Success ", "You are Successfully Registered");
			} else {
				((ObjectNode) dataResponse).put("Error ", "Error in Registration");
			}
		} catch (Exception exp) {
			((ObjectNode) dataResponse).put("Error ", exp.getMessage());

		}
		return dataResponse.toString();
	}

	@ResponseBody
	@PostMapping("/adminRegistration")
	public String adminRegistration(@RequestBody Map<String, Object> requestData) {
		String adminName = requestData.get("adminName").toString();
		String adminId = requestData.get("adminId").toString();
		String adminMail = requestData.get("adminMail").toString();
		String adminPassword = requestData.get("adminPassword").toString();
		long adminNumber = Long.parseLong(requestData.get("adminNumber").toString());

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode dataResponse = objectMapper.createObjectNode();

		boolean result = false;
		try {
			adminPassword = Encryptor.encrypt(adminPassword, "Busnama");
			User admin = new User(adminName, adminId, adminMail, adminPassword, adminNumber, 1, false);
			result = userService.adminRegistration(admin);
			if (result == true) {
				((ObjectNode) dataResponse).put("Success ", "You are Successfully Registered");
			} else {
				((ObjectNode) dataResponse).put("Error ", "Error in Registration");
			}

		} catch (Exception exp) {
			((ObjectNode) dataResponse).put("Error ", exp.getMessage());
		}
		return dataResponse.toString();

	}

	@ResponseBody
	@PostMapping("/login")
	public String login(@RequestBody Map<String, Object> requestData) {
		String userId = requestData.get("userId").toString();
		String password = requestData.get("userPassword").toString();
		User user = new User(null, userId, null, password, 0L, 1, false);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode dataResponse = objectMapper.createObjectNode();
		User result = null;
		String userFetched = null;
		try {
			result = userService.userLogin(user);
			if (result != null) {
				((ObjectNode) dataResponse).put("Success ", "Successfully Logged In");
				userFetched = objectMapper.writeValueAsString(result);
				dataResponse = objectMapper.readTree(userFetched);
			}
		} catch (Exception exp) {
			((ObjectNode) dataResponse).put("Error ", exp.getMessage());

		}
		return dataResponse.toString();
	}

	@ResponseBody
	@PostMapping("/logout")
	public String logout(@RequestBody Map<String, Object> requestData) {
		String userId = requestData.get("userId").toString();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode dataResponse = objectMapper.createObjectNode();
		boolean result = false;
		try {
			result = userService.logout(userId);
			if (result == true) {
				((ObjectNode) dataResponse).put("Success ", "Successfully logged out");
			} else {
				((ObjectNode) dataResponse).put("Error ", "Error in logging out");
			}
		} catch (Exception exp) {
			((ObjectNode) dataResponse).put("Error", exp.getMessage());

		}

		return dataResponse.toString();
	}

	@ResponseBody
	@PostMapping("/usersList")
	public List<User> getUserList() {
		List<User> usersList = null;
		try {
			usersList = userService.getUserList();
		} catch (Exception exp) {

		}

		return usersList;
	}
}
