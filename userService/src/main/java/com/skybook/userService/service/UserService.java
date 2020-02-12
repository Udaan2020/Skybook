package com.skybook.userService.service;

import java.util.List;

import com.skybook.userService.exception.UserException;
import com.skybook.userService.model.User;

public interface UserService {

	public boolean customerRegistration(User customer) throws UserException;
	
	public boolean adminRegistration(User admin) throws UserException;
	
	public User userLogin(User user) throws UserException;
	
	public boolean logout(String userId) throws UserException;
	
	public List<User> getUserList() throws UserException;
}
