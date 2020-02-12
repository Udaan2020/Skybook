package com.capgemini.uraan.skybook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.uraan.skybook.exceptions.FlightException;
import com.capgemini.uraan.skybook.models.Airlines;
import com.capgemini.uraan.skybook.models.Flight;
import com.capgemini.uraan.skybook.services.AirlineService;

@RestController
@RequestMapping("/api/airlines")
public class AirlinesController {

	public AirlinesController() {
		
	}
	@Autowired
	private AirlineService airlineService;
	
	@GetMapping("/list")
	public List<Airlines> list()
	{
		return airlineService.list();
	}
	
	@GetMapping("/get/{id}")
	public Airlines get(@PathVariable("id") String id) 
	{
		Airlines airline = null;
		try
		{
			airline = airlineService.get(id);
			return airline;
		}
		catch(FlightException fe)
		{
			 throw fe;
		}
		
		
		
	}

}
