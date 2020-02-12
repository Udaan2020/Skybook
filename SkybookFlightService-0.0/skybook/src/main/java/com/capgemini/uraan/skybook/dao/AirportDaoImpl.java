package com.capgemini.uraan.skybook.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.uraan.skybook.constants.FlightExceptionConstants;
import com.capgemini.uraan.skybook.exceptions.FlightException;
import com.capgemini.uraan.skybook.models.Airport;
import com.capgemini.uraan.skybook.models.Flight;
import com.capgemini.uraan.skybook.repositories.AirportRepository;

@Component
public class AirportDaoImpl implements AirportDao {

	@Autowired
	private AirportRepository airportRepository;

	@Override
	public List<Airport> list() {

		return airportRepository.findAll();
	}

	@Override
	public Airport get(String id)throws FlightException {
		try {
			Airport airport =airportRepository.findById(id).orElseThrow(() -> new FlightException(FlightExceptionConstants.NO_AIRPORT_EXISTS));
			return airport;
		}
		catch(FlightException fe)
		{
			throw fe;
		}
	}

}
