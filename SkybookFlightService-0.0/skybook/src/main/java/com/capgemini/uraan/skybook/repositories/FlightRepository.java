package com.capgemini.uraan.skybook.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.uraan.skybook.models.Airport;
import com.capgemini.uraan.skybook.models.Flight;

public interface FlightRepository extends JpaRepository<Flight, String> {

	List<Flight> findBySourceAirportAndDestinationAirport(Airport source, Airport destination);
}
