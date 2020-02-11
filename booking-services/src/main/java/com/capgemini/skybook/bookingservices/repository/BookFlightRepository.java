package com.capgemini.skybook.bookingservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.skybook.bookingservices.models.Booking;

public interface BookFlightRepository extends JpaRepository<Booking, String> {

	List<Booking> findByUserId(String userId);
}
