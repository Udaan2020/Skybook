package com.skybook.uraaan.passengerservice.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skybook.uraaan.passengerservice.models.Passenger;
import com.skybook.uraaan.passengerservice.repositories.PassengerRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/booking/passenger")
public class PassengerController {

	@Autowired
	private PassengerRepository passenRepo;
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.OK)
	public void add(@Valid @RequestBody Passenger passenger)
	{
		passenRepo.saveAndFlush(passenger);
	}
	
	@GetMapping("/get/{pnr}")
	public List<Passenger> getByPnr(@PathVariable("pnr") String pnr)
	{
		return passenRepo.findByPnr(pnr);
		
	}

	@GetMapping("/get")
	public List<Passenger> get()
	{
		return passenRepo.findAll();
	}
	
	 @RequestMapping(value = "/all/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	 public  void addPassengers(@RequestBody List<Passenger> passengers) {
		 System.out.println("controller");
	     System.out.println(passengers);
		 passenRepo.saveAll(passengers);
	 }
}
