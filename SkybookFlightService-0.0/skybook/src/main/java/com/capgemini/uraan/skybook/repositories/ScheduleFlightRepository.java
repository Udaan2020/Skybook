package com.capgemini.uraan.skybook.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.uraan.skybook.models.Flight;
import com.capgemini.uraan.skybook.models.ScheduleFlight;

public interface ScheduleFlightRepository extends JpaRepository<ScheduleFlight, Long> {
	
	public List<ScheduleFlight> findByDepartureDateOrderByEconomicPriceAsc(Date dept);
	public void deleteByFlight(Flight flight);

}
