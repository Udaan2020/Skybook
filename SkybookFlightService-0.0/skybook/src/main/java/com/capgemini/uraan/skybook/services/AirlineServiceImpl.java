package com.capgemini.uraan.skybook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.uraan.skybook.dao.AirlinesDao;
import com.capgemini.uraan.skybook.exceptions.FlightException;
import com.capgemini.uraan.skybook.models.Airlines;

@Service
public class AirlineServiceImpl implements AirlineService {

	public AirlineServiceImpl() {
		
	}
	
	@Autowired
	private AirlinesDao airlinesDao;

	@Override
	public List<Airlines> list() {
		
		return airlinesDao.list();
	}

	@Override
	public Airlines get(String id) throws FlightException {
		return airlinesDao.get(id);
	}
}
