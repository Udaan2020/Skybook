package com.skybook.uraaan.passengerservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skybook.uraaan.passengerservice.models.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
	
	public List<Passenger> findByPnr(String pnr);

}
