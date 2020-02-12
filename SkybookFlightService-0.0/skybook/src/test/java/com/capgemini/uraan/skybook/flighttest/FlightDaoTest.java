package com.capgemini.uraan.skybook.flighttest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.uraan.skybook.dao.AirlinesDao;
import com.capgemini.uraan.skybook.dao.AirportDao;
import com.capgemini.uraan.skybook.dao.FlightDao;
import com.capgemini.uraan.skybook.exceptions.FlightException;
import com.capgemini.uraan.skybook.models.Airlines;
import com.capgemini.uraan.skybook.models.Airport;
import com.capgemini.uraan.skybook.models.Flight;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightDaoTest {

	
	
	@Autowired
	private FlightDao flightDao;
	
	@Autowired
	private AirlinesDao airlinesDao;
	
	@Autowired
	private AirportDao airportDao;
	
	@Test
	@DisplayName("Flight Registration Successful")
	@Rollback(true)
	public void addFlight()
	{
		Flight flight = new Flight();
		Airport src = airportDao.get("CCU");
		Airport dest = airportDao.get("DEL");
		Airlines airop = airlinesDao.get("ARIN");
		flight.setAirlines(airop);
		flight.setFlightNo("ARIN098");
		flight.setSourceAirport(src);
		flight.setDestinationAirport(dest);
		flight.setBusinessSeat(100);
		flight.setEconomySeat(200);
		flight.setCheckInLuggage(10);
		flight.setCabinLuggage(20);
		flight.setModel("AIRBUS 237");
		boolean success = false;
		try
		{
			flightDao.create(flight);
			success = true;
		}
		catch(FlightException fe)
		{
			success = false;
		}
		assertTrue(success);
	}
	
	@Test
	@DisplayName("Invalid Field in Flight Registration")
	@Rollback(true)
	public void invalidAddFlight()
	{
		Flight flight = new Flight();
		flight.setFlightNo("INDG122");
		String err = "";
		try
		{
			flightDao.create(flight);
		}
		catch(FlightException fe)
		{
			err=fe.getMessage();
		}
		assertEquals("All fields are required", err);
	}
	
	@Test
	@DisplayName("Existing Flight ID")
	@Rollback(true)
	public void invalidAddFlight3()
	{
		Flight flight = new Flight();
		Airport src = airportDao.get("CCU");
		Airport dest = airportDao.get("DEL");
		Airlines airop = airlinesDao.get("ARIN");
		flight.setAirlines(airop);
		flight.setFlightNo("ARAS444");
		flight.setSourceAirport(src);
		flight.setDestinationAirport(dest);
		flight.setBusinessSeat(100);
		flight.setEconomySeat(200);
		flight.setCheckInLuggage(10);
		flight.setCabinLuggage(20);
		flight.setModel("AIRBUS 237");
		String err = "";
		try
		{
			flightDao.create(flight);
		}
		catch(FlightException fe)
		{
			err=fe.getMessage();
		}
		assertEquals("Flight with entered ID already Exists", err);
	}
	@Test
	@DisplayName("Same Source and Destination")
	@Rollback(true)
	public void invalidAddFlight2()
	{
		Flight flight = new Flight();
		Airport src = airportDao.get("CCU");
		Airport dest = airportDao.get("CCU");
		Airlines airop = airlinesDao.get("ARIN");
		flight.setAirlines(airop);
		flight.setFlightNo("ARIN1911");
		flight.setSourceAirport(src);
		flight.setDestinationAirport(dest);
		flight.setBusinessSeat(100);
		flight.setEconomySeat(200);
		flight.setCheckInLuggage(10);
		flight.setCabinLuggage(20);
		flight.setModel("AIRBUS 237");
		String err = "";
		try
		{
			flightDao.create(flight);
		}
		catch(FlightException fe)
		{
			err=fe.getMessage();
		}
		assertEquals("Source and Destination should be different", err);
	}
	
	@Test
	@DisplayName("Null Value")
	@Rollback(true)
	public void invalidFlight4()
	{
		  assertThrows(NullPointerException.class,()-> flightDao.create(null));
	}

}
