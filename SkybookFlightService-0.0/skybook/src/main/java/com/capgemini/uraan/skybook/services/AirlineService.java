package com.capgemini.uraan.skybook.services;

import java.util.List;

import com.capgemini.uraan.skybook.exceptions.FlightException;
import com.capgemini.uraan.skybook.models.Airlines;

public interface AirlineService {
	
	public List<Airlines> list();
	public Airlines get(String id)throws FlightException;
}
