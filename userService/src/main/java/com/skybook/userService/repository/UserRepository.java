package com.skybook.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skybook.userService.model.User;

public interface UserRepository extends JpaRepository<User, String>{

}
