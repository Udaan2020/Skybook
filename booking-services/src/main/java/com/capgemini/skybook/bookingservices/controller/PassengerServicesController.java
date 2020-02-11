package com.capgemini.skybook.bookingservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.skybook.bookingservices.models.Passengers;
import com.capgemini.skybook.bookingservices.repository.PassengerRepository;

@RestController
@RequestMapping("/api/booking/passengers")
public class PassengerServicesController {

	@Autowired
	private PassengerRepository passengRepo;

	@GetMapping("/get/booking/{id}")
	public List<Passengers> getByBooking(String bookingId) {
		return passengRepo.findByBookingId(bookingId);
	}
	
	@PostMapping("/passangers/add")
	public void addPassangers(List<Passengers> passangers)
	{
		for(Passengers p : passangers)
		{
			passengRepo.saveAndFlush(p);
		}
	}
}
