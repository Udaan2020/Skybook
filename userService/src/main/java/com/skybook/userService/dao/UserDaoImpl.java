package com.skybook.userService.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skybook.userService.exception.UserException;
import com.skybook.userService.model.User;
import com.skybook.userService.repository.UserRepository;
import com.skybook.userService.utility.Encryptor;

@Repository(value = "userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

//	@Autowired 
//	private UserRepository userRepository;
//	
//	
//	public UserRepository getUserRepository() {
//		return userRepository;
//	}
//
//	public void setUserRepository(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}

	@Override
	public boolean customerRegistration(User customer) throws UserException {
		boolean userRegistrationStatus = false;
		Session session = null;
		Transaction transaction = null;
		CriteriaBuilder criteriaBuilder = null;
		User existingCustomer = null;
		try {
			session = getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			existingCustomer = session.find(User.class, customer.getUserId());
			if (existingCustomer != null) {
				throw new UserException("User Already Registered!");
			}
			List<User> customers = new ArrayList<User>();

			// Email uniqueness validation
			criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
			Root<User> existingCustomr = criteriaQuery.from(User.class);
			criteriaQuery.where(criteriaBuilder.equal(existingCustomr.get("userMail"), customer.getUserMail()));
			customers = session.createQuery(criteriaQuery).getResultList();
			if (customers.size() != 0) {
				throw new UserException("Email already exists !");
			}
			// Phone Number uniqueness validation
			criteriaBuilder = session.getCriteriaBuilder();
			criteriaQuery = criteriaBuilder.createQuery(User.class);
			existingCustomr = criteriaQuery.from(User.class);
			criteriaQuery.where(criteriaBuilder.equal(existingCustomr.get("userNumber"), customer.getUserNumber()));
			customers = session.createQuery(criteriaQuery).getResultList();
			if (customers.size() != 0) {
				throw new UserException("Phone number already exists !");
			}
			session.save(customer);
			transaction.commit();
			userRegistrationStatus = true;
		} catch (Exception exp) {
			transaction.rollback();
			throw new UserException("Error in Registration: " + exp.getMessage());

		} finally {
			session.close();
		}
		return userRegistrationStatus;
	}

	@Override
	public boolean adminRegistration(User admin) throws UserException {
		boolean adminRegistrationStatus = false;
		Session session = null;
		Transaction transaction = null;
		CriteriaBuilder criteriaBuilder = null;
		User existingAdmin = null;
		try {
			session = getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			existingAdmin = session.find(User.class, admin.getUserId());
			if (existingAdmin != null) {
				throw new UserException("User Already Registered !!");
			}
			List<User> admins = new ArrayList<User>();

			// EmailId uniqueness validation
			criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
			Root<User> existingAdminn = criteriaQuery.from(User.class);
			criteriaQuery.where(criteriaBuilder.equal(existingAdminn.get("userMail"), admin.getUserMail()));
			admins = session.createQuery(criteriaQuery).getResultList();
			if (admins.size() != 0) {
				throw new UserException("Admin's mail already exists");
			}

			// Phone number uniqueness validation
			criteriaBuilder = session.getCriteriaBuilder();
			criteriaQuery = criteriaBuilder.createQuery(User.class);
			existingAdminn = criteriaQuery.from(User.class);
			criteriaQuery.where(criteriaBuilder.equal(existingAdminn.get("userNumber"), admin.getUserNumber()));
			admins = session.createQuery(criteriaQuery).getResultList();
			if (admins.size() != 0) {
				throw new UserException("Admin's phone number already exists");
			}
			session.save(admin);
			transaction.commit();
			adminRegistrationStatus = true;
		} catch (Exception exp) {
			transaction.rollback();
			throw new UserException("Error in Registration: " + exp.getMessage());

		} finally {
			session.close();
		}

		return adminRegistrationStatus;
	}

	@Override
	public User userLogin(User user) throws UserException {
		Session session = null;
		Transaction transaction = null;
		User existingUser = null;
		try {
			session = getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			existingUser = session.find(User.class, user.getUserId());
			if (existingUser == null) {
				throw new UserException("No such user id Exists");
			} else if (existingUser.isUserActiveStatus() == true) {
				throw new UserException("You are already logged in");
			} else if (Encryptor.encrypt(user.getUserPassword(), "Busnama").equals(existingUser.getUserPassword())) {
				existingUser.setUserActiveStatus(true);
				transaction.commit();
			} else {
				throw new UserException("Incorrect Password");
			}
		} catch (Exception exp) {
			transaction.rollback();
			throw new UserException("Error in log in: " + exp.getMessage());
		} finally {
			session.close();
		}
		return existingUser;
	}

	@Override
	public boolean logout(String userId) throws UserException {
		boolean userLogoutStatus = false;
		Session session = null;
		Transaction transaction = null;
		User existingUser = null;
        try {
        	session = getSessionFactory().openSession();
        	transaction = session.getTransaction();
        	transaction.begin();
        	existingUser = session.find(User.class, userId);
        	if(existingUser == null) {
        		throw new UserException("No such user id Exists");
        	}
        	existingUser.setUserActiveStatus(false);
        	transaction.commit();
        	userLogoutStatus = true;
        } catch (Exception exp) {
        	transaction.rollback();
        	throw new UserException("Error in logout: " + exp.getMessage());
        } finally {
        	session.close();
        }
		return userLogoutStatus;
	}

	@Override
	public User getUserById(String userId) throws UserException {
		Session session = null;
		Transaction transaction = null;
		User existingUser = null;
		try {
			session = getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			existingUser = session.find(User.class, userId);
			//existingUser = userRepository.getOne(userId);
			if (existingUser == null) {
				throw new UserException("No such user id Exists");
			}
			transaction.commit();
		}catch (Exception exp) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return existingUser;
	}

	@Override
	public List<User> getUserList() throws UserException {
		List<User> users = null;
		Session session = null;
		Transaction transaction = null;
		CriteriaBuilder criteriaBuilder = null;
		try {
			session = getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
			Root<User> userTable = criteriaQuery.from(User.class);
			criteriaQuery.select(userTable);
			users = session.createQuery(criteriaQuery).getResultList();
			if (users.size() == 0) {
				throw new UserException("No users present");
			}
			transaction.commit();
		} catch (Exception exp) {
			transaction.rollback();
			throw new UserException("Error in getting users: " + exp.getMessage());
		} finally {
			session.close();
		}
		/*
		users = userRepository.findAll();
		if (users.size() == 0) {
			throw new UserException("No users present");
		}*/
		return users;
	}

}
