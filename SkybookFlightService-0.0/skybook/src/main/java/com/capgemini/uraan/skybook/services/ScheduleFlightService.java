package com.capgemini.uraan.skybook.services;

import java.sql.Date;
import java.util.List;

import com.capgemini.uraan.skybook.models.ScheduleFlight;


public interface ScheduleFlightService {

	public List<ScheduleFlight> list();

	public void create(ScheduleFlight scheduleFlight);

	public ScheduleFlight get(long id);

	public void delete(long id);

	public List<ScheduleFlight> findByDate(Date departureDate, String source, String Destination);

	public void update(ScheduleFlight scheduleFlight);
}
