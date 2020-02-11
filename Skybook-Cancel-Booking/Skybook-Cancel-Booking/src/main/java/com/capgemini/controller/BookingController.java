package com.capgemini.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entity.Booking;
import com.capgemini.service.BookingService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	BookingService bs;
	@GetMapping("/get/{bookingId}")
	Optional<Booking> getBookingById(@PathVariable String bookingId) {
		return bs.getBookingById(bookingId);
		
	}
	
	@DeleteMapping("/remove/{bookingId}")
	boolean deleteMappingById(@PathVariable String bookingId) {
		return bs.cancelBooking(bookingId);
	}
}
