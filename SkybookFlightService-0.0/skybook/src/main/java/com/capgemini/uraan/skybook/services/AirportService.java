package com.capgemini.uraan.skybook.services;

import java.util.List;

import com.capgemini.uraan.skybook.exceptions.FlightException;
import com.capgemini.uraan.skybook.models.Airport;

public interface AirportService {
	
	public List<Airport> list();
	public Airport get(String id)throws FlightException;

}
