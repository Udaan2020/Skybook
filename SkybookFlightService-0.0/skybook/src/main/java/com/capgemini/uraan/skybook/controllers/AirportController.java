package com.capgemini.uraan.skybook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.uraan.skybook.exceptions.FlightException;
import com.capgemini.uraan.skybook.models.Airport;
import com.capgemini.uraan.skybook.models.Flight;
import com.capgemini.uraan.skybook.services.AirportService;

@RestController
@RequestMapping("/api/airport")
public class AirportController {

	public AirportController() {
		
	}
	@Autowired
	private AirportService airportService;
	
	@GetMapping("/list")
	public List<Airport> list()
	{
		return airportService.list();
	}
	
	@GetMapping("/get/{id}")
	public Airport get(@PathVariable("id") String id) 
	{
		Airport airport = null;
		try
		{
			airport = airportService.get(id);
			return airport;
		}
		catch(FlightException fe)
		{
			 throw fe;
		}
		
		
		
	}

}
