package com.capgemini.skybook.bookingservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.skybook.bookingservices.models.Passengers;

public interface PassengerRepository extends JpaRepository<Passengers, String> {

	List<Passengers> findByBookingId(String bookingId);
}
