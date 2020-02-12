package com.skybook.userService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skybook.userService.dao.UserDao;
import com.skybook.userService.exception.UserException;
import com.skybook.userService.model.User;

@Service(value="userService")
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	public boolean customerRegistration(User customer) throws UserException {
		
		return userDao.customerRegistration(customer);
	}


	@Override
	public boolean adminRegistration(User admin) throws UserException {
		
		return userDao.adminRegistration(admin);
	}


	@Override
	public User userLogin(User user) throws UserException {
		
		return userDao.userLogin(user);
	}


	@Override
	public boolean logout(String userId) throws UserException {
		
		return userDao.logout(userId);
	}
	
	
	@Override
	public List<User> getUserList() throws UserException {
		return userDao.getUserList();
	}
}
