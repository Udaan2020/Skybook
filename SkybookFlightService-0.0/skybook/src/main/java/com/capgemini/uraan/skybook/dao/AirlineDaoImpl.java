package com.capgemini.uraan.skybook.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.uraan.skybook.constants.FlightExceptionConstants;
import com.capgemini.uraan.skybook.exceptions.FlightException;
import com.capgemini.uraan.skybook.models.Airlines;
import com.capgemini.uraan.skybook.models.Airport;
import com.capgemini.uraan.skybook.repositories.AirlineRepository;

@Component
public class AirlineDaoImpl implements AirlinesDao{

	public AirlineDaoImpl() {
		
	}
	
	@Autowired
	private AirlineRepository airlineRepository;
	
	@Override
	public List<Airlines> list() {
		
		return airlineRepository.findAll();
	}

	@Override
	public Airlines get(String id) {
		try {
			Airlines airline =airlineRepository.findById(id).orElseThrow(() -> new FlightException(FlightExceptionConstants.NO_AIRLINE_EXISTS));
			return airline;
		}
		catch(FlightException fe)
		{
			throw fe;
		}
	}

}
