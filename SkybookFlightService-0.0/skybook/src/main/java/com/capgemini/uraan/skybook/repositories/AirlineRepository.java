package com.capgemini.uraan.skybook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.uraan.skybook.models.Airlines;

public interface AirlineRepository extends JpaRepository<Airlines, String> {

}
