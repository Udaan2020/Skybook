package com.capgemini.uraan.skybook.controllers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.uraan.skybook.models.ScheduleFlight;
import com.capgemini.uraan.skybook.services.ScheduleFlightService;


@RestController
@RequestMapping("/api/schedule-flight")
public class ScheduleFlightController {

	@Autowired
	private ScheduleFlightService scheduleFlightService;
	
	@GetMapping("/list")
	public List<ScheduleFlight> list()
	{
		return scheduleFlightService.list();
		
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.OK)
	public void create(@RequestBody ScheduleFlight scheduleFlight)
	{
		scheduleFlightService.create(scheduleFlight);
	}
	
	@GetMapping("/{id}")
	public ScheduleFlight get(@PathVariable("id") String id)
	{
		return scheduleFlightService.get(Long.parseLong(id));
	}
	
	@DeleteMapping("remove/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") long id)
	{
		scheduleFlightService.delete(id);
	}
	
	@GetMapping("{dept}/{source}/{destination}")
	public List<ScheduleFlight> findByDateandLocation(@PathVariable("dept") String dept ,@PathVariable("source") String src, @PathVariable("destination") String dst) throws ParseException
	{

		String unit[] = dept.split("-");
		int day = Integer.valueOf(unit[2]) + 1;
		unit[2] = Integer.toString(day);
		dept = unit[0] + '-' + unit[1] + '-' + unit[2];
		Date deptDt = Date.valueOf(dept);
		return scheduleFlightService.findByDate(deptDt, src, dst);
	}
	
	@PutMapping("update")
	@ResponseStatus(HttpStatus.OK)
	public void updateFlight(@RequestBody ScheduleFlight scheduleFlight)
	{
		
		scheduleFlightService.update(scheduleFlight);
	}
	
}
