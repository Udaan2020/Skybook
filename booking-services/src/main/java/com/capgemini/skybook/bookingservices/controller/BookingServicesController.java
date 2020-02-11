package com.capgemini.skybook.bookingservices.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.skybook.bookingservices.models.Booking;
import com.capgemini.skybook.bookingservices.repository.BookFlightRepository;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/booking")
public class BookingServicesController {

	@Autowired
	private BookFlightRepository bookingRepo;

	@PostMapping("/book")
	@ResponseStatus(HttpStatus.OK)
	public void book(@Valid @RequestBody Booking booking) {
		bookingRepo.saveAndFlush(booking);
	}
	
	@GetMapping("/all")
	public List<Booking> list()
	{
		return bookingRepo.findAll();
	}

	@DeleteMapping("remove/{id}")
	public void delete( @PathVariable("id") String id) {
		Booking book = bookingRepo.findById(id).get();
		bookingRepo.delete(book);
	}

	@GetMapping("/get/{id}")
	public Booking get(@PathVariable("id") String id) {
		return bookingRepo.getOne(id);
	}

	@GetMapping("/get/user/{id}")
	public List<Booking> getByUser(@PathVariable("id") String uid) {
		return bookingRepo.findByUserId(uid);
	}

}
