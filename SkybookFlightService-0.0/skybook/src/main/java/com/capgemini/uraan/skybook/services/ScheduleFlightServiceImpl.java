package com.capgemini.uraan.skybook.services;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.uraan.skybook.dao.ScheduleFlightDao;
import com.capgemini.uraan.skybook.models.ScheduleFlight;

@Service
public class ScheduleFlightServiceImpl implements ScheduleFlightService {

	@Autowired
	private ScheduleFlightDao scheduleFlightDao;
	
	@Override
	public List<ScheduleFlight> list() {
		
		return scheduleFlightDao.list();
	}

	@Override
	public void create(ScheduleFlight scheduleFlight) {
		
		scheduleFlightDao.create(scheduleFlight);

	}

	@Override
	public ScheduleFlight get(long id) {
		
		return scheduleFlightDao.get(id);
	}

	@Override
	public void delete(long id) {
		
		scheduleFlightDao.delete(id);

	}

	@Override
	public List<ScheduleFlight> findByDate(Date departureDate, String source, String destination) {
		
		return scheduleFlightDao.findByDate(departureDate).stream()
				.filter(sf -> sf.getFlight().getSourceAirport().getCity().equalsIgnoreCase(source))
				.filter(sf -> sf.getFlight().getDestinationAirport().getCity().equalsIgnoreCase(destination))
				.collect(Collectors.toList());
		
	}

	@Override
	public void update(ScheduleFlight scheduleFlight) {
		
		scheduleFlightDao.update(scheduleFlight);

	}

}
