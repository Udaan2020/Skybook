package com.capgemini.uraan.skybook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.uraan.skybook.dao.FlightDao;
import com.capgemini.uraan.skybook.exceptions.FlightException;
import com.capgemini.uraan.skybook.models.Flight;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightDao flightDao;
	
	@Override
	public List<Flight> list() throws FlightException {
		
		return flightDao.list();
	}

	@Override
	public void create(Flight flight) throws FlightException {
		
		flightDao.create(flight);
	}

	@Override
	public Flight get(String id) throws FlightException {
		
		return flightDao.get(id);
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
	@Override
	public void delete(String id) throws FlightException {
		
		flightDao.delete(id);

	}

	@Override
	public List<Flight> findBySourceAndDestination(String src, String dst) throws FlightException {
		
		return flightDao.findBySourceAndDestination(src, dst);
	}

	@Override
	public void updateFlight(Flight flight) throws FlightException {
		
		flightDao.updateFlight(flight);

	}

}
