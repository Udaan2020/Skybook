package com.capgemini.uraan.skybook.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class ScheduleFlight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
	private Date departureDate;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
	private Date arrivalDate;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private Time departureTime;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private Time arrivalTime;
	
	@NotNull
	private BigDecimal economicPrice;
	
	@NotNull
	private BigDecimal businessPrice;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "flight_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Flight flight;
	
	@NotNull
	private String flightStatus;
	
	@NotNull
	private int economicBookedSeats;
	
	@NotNull
	private int businessBookedSeats;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Time getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public BigDecimal getEconomicPrice() {
		return economicPrice;
	}

	public void setEconomicPrice(BigDecimal economicPrice) {
		this.economicPrice = economicPrice;
	}

	public BigDecimal getBusinessPrice() {
		return businessPrice;
	}

	public void setBusinessPrice(BigDecimal businessPrice) {
		this.businessPrice = businessPrice;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public String getFlightStatus() {
		return flightStatus;
	}

	public void setFlightStatus(String flightStatus) {
		this.flightStatus = flightStatus;
	}

	public int getEconomicBookedSeats() {
		return economicBookedSeats;
	}

	public void setEconomicBookedSeats(int economicBookedSeats) {
		this.economicBookedSeats = economicBookedSeats;
	}

	public int getBusinessBookedSeats() {
		return businessBookedSeats;
	}

	public void setBusinessBookedSeats(int businessBookedSeats) {
		this.businessBookedSeats = businessBookedSeats;
	}
}
