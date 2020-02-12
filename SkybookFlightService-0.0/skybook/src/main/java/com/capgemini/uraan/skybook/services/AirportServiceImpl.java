package com.capgemini.uraan.skybook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.uraan.skybook.dao.AirportDao;
import com.capgemini.uraan.skybook.exceptions.FlightException;
import com.capgemini.uraan.skybook.models.Airport;

@Service
public class AirportServiceImpl implements AirportService {

	@Autowired
	private AirportDao airportDao;
	
	public AirportServiceImpl() {
		
	}

	@Override
	public List<Airport> list() {
		
		return airportDao.list();
	}

	@Override
	public Airport get(String id) throws FlightException {
		
		return airportDao.get(id);
	}

	

}
