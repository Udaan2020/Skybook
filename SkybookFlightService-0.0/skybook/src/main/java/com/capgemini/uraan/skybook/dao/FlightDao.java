package com.capgemini.uraan.skybook.dao;

import java.util.List;

import com.capgemini.uraan.skybook.exceptions.FlightException;
import com.capgemini.uraan.skybook.models.Flight;

public interface FlightDao {

	public List<Flight> list() throws FlightException;

	public void create(Flight flight) throws FlightException;

	public Flight get(String id) throws FlightException;
	
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

	public void delete(String id) throws FlightException;

	public List<Flight> findBySourceAndDestination(String src,String dst) throws FlightException;

	public void updateFlight(Flight flight) throws FlightException;

}
