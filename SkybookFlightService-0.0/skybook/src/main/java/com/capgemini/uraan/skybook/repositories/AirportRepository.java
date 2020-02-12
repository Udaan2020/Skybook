package com.capgemini.uraan.skybook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.uraan.skybook.models.Airport;

public interface AirportRepository extends JpaRepository<Airport, String> {

}
