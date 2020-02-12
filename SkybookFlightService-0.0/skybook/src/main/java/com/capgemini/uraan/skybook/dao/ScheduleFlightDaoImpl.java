package com.capgemini.uraan.skybook.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.uraan.skybook.models.ScheduleFlight;
import com.capgemini.uraan.skybook.repositories.ScheduleFlightRepository;

@Component
public class ScheduleFlightDaoImpl implements ScheduleFlightDao {

	@Autowired
	private ScheduleFlightRepository scheduleFlightRepository;

	@Override
	public List<ScheduleFlight> list() {

		return scheduleFlightRepository.findAll();
	}

	@Override
	public void create(ScheduleFlight scheduleFlight) {

		scheduleFlightRepository.saveAndFlush(scheduleFlight);

	}

	@Override
	public ScheduleFlight get(long id) {

		return scheduleFlightRepository.findById(id).get();
	}

	@Override
	public void delete(long id) {

		ScheduleFlight scheduleFlight = scheduleFlightRepository.getOne(id);
		scheduleFlightRepository.delete(scheduleFlight);

	}

	@Override
	public List<ScheduleFlight> findByDate(Date departureDate) {

		return scheduleFlightRepository.findByDepartureDateOrderByEconomicPriceAsc(departureDate);
	}

	@Override
	public void update(ScheduleFlight scheduleFlight) {

		if (scheduleFlightRepository.existsById(scheduleFlight.getId())) {
			scheduleFlightRepository.saveAndFlush(scheduleFlight);
		}

	}

}
