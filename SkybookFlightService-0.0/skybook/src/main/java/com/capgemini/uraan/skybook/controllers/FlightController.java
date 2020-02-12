package com.capgemini.uraan.skybook.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.uraan.skybook.constants.FlightExceptionConstants;
import com.capgemini.uraan.skybook.exceptions.FlightException;
import com.capgemini.uraan.skybook.models.Flight;
import com.capgemini.uraan.skybook.repositories.FlightRepository;
import com.capgemini.uraan.skybook.services.FlightService;


@RestController
@RequestMapping("/api/flight")
public class FlightController {

	@Autowired
	private FlightService flightService;
	
	@Autowired
	private FlightRepository flightRepository;

	
	@GetMapping("/list")
	public List<Flight> list()
	{
		try
		{
			return flightService.list();
		}
		catch(FlightException fe)
		{
			throw  fe;
		}
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.OK)
	public void create( @Valid @RequestBody Flight flight) 
	{
		try
		{
			flightService.create(flight);
		}
		catch(FlightException fe)
		{
			throw fe;
		}
	}
	
	@GetMapping("/get/{id}")
	public Flight get(@PathVariable("id") String id) 
	{
		Flight flight = null;
		try
		{
			flight = flightService.get(id);
			return flight;
		}
		catch(FlightException fe)
		{
			 throw fe;
		}
		
		
		
	}
	
	/**********************************************/
	/*function name : delete
	 * Return type : void
	 * Desc : to delete a flight from database
	 * @param : String flight id
	 * @throws : Flight Exception
	 * Author : Agnibha Chandra
	 * Date : 29/01/2020
	 */
	/**********************************************/
	@DeleteMapping("remove/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") String id) 
	{
		try
		{
			flightService.delete(id);
		}
		catch(FlightException fe)
		{
			throw fe;
		}
	}
	
	@GetMapping("/{source}/{destination}")
	public List<Flight> findBySourceAndDestination(@PathVariable("source") String src, @PathVariable("destination") String dst)
	{
		try
		{
			return flightService.findBySourceAndDestination(src, dst);
		}
		catch(FlightException fe)
		{
			throw fe;
		}
	}
	
	@PutMapping("update")
	@ResponseStatus(HttpStatus.OK)
	public void updateFlight(@RequestBody Flight flight) 
	{
		try
		{
			flightService.updateFlight(flight);
		}
		catch(FlightException fe)
		{
			throw fe;
		}
	}
	
}
