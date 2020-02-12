package com.capgemini.uraan.skybook.dao;

import java.util.List;

import com.capgemini.uraan.skybook.exceptions.FlightException;
import com.capgemini.uraan.skybook.models.Airlines;

public interface AirlinesDao {

	public List<Airlines> list();
	public Airlines get(String id)throws FlightException;
}
