package com.capgemini.uraan.skybook.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.uraan.skybook.constants.FlightExceptionConstants;
import com.capgemini.uraan.skybook.exceptions.FlightException;
import com.capgemini.uraan.skybook.models.Airport;
import com.capgemini.uraan.skybook.models.Flight;
import com.capgemini.uraan.skybook.repositories.AirportRepository;
import com.capgemini.uraan.skybook.repositories.FlightRepository;
import com.capgemini.uraan.skybook.repositories.ScheduleFlightRepository;

@Component
public class FlightDaoImpl implements FlightDao {

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private AirportRepository airportRepository;
	
	@Autowired
	private ScheduleFlightRepository sfRepo;

	@Override
	public List<Flight> list() throws FlightException {
		
		try
		{
			return flightRepository.findAll();
		}
		catch(Exception ex)
		{
			throw new FlightException(ex.getMessage());
		}
	}

	@Override
	public void create(Flight flight) throws FlightException {
		if(flightRepository.existsById(flight.getFlightNo()))
		{
			throw new FlightException(FlightExceptionConstants.FLIGHT_ALREADY_EXISTS);
		}
		else if(flight.getAirlines() == null || flight.getDestinationAirport() == null || flight.getSourceAirport() == null
				|| flight.getFlightNo() == null || flight.getModel() == null)
		{
			throw new FlightException(FlightExceptionConstants.INVALID_FIELD);
		}
		else if(flight.getSourceAirport().getAirportName().equals(flight.getDestinationAirport().getAirportName()))
		{
			throw new FlightException(FlightExceptionConstants.SAME_SRC_DEST);
		}
		else
		{
			flightRepository.saveAndFlush(flight);
		}
	}

	@Override
	public Flight get(String id) throws FlightException {
		
		try {
			Flight flight =flightRepository.findById(id).orElseThrow(() -> new FlightException(FlightExceptionConstants.GET_FLIGHT_EXCEPTION));
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

	@Override
	public void delete(String id) throws FlightException {
		try
		{
			Flight flight =flightRepository.findById(id).orElseThrow(() -> new FlightException(FlightExceptionConstants.GET_FLIGHT_EXCEPTION));
			sfRepo.deleteByFlight(flight);
			flightRepository.delete(flight);
		}
		catch(Exception fe)
		{
			throw new FlightException(FlightExceptionConstants.GET_FLIGHT_EXCEPTION);
		}
	}

	@Override
	public List<Flight> findBySourceAndDestination(String src, String dst) throws FlightException {
		
		if(src.equalsIgnoreCase(dst))
		{
			throw new FlightException(FlightExceptionConstants.SAME_SRC_DEST);
		}
		else
		{
			try
			{
				Airport source = airportRepository.getOne(src);
				Airport destination = airportRepository.getOne(dst);
				List<Flight>  flights = flightRepository.findBySourceAirportAndDestinationAirport(source, destination);
				return flights;
			}
			catch(Exception fe)
			{
				throw new FlightException(FlightExceptionConstants.WRONG_QUERIES);
			}
		}
	}

	@Override
	public void updateFlight(Flight flight) throws FlightException {
		
		if(flightRepository.existsById(flight.getFlightNo()))
		{
			flightRepository.saveAndFlush(flight);
		}
		else
		{
			throw new FlightException(FlightExceptionConstants.GET_FLIGHT_EXCEPTION);
		}
	}

}
