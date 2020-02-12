package com.skybook.userService.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.skybook.userService.exception.UserException;
import com.skybook.userService.model.User;

@Component
public interface UserDao {
	
	boolean customerRegistration(User customer) throws UserException;

	boolean adminRegistration(User admin) throws UserException;

	User userLogin(User user) throws UserException;

	boolean logout(String userId) throws UserException;

	User getUserById(String userId) throws UserException;

	List<User> getUserList() throws UserException;
}
