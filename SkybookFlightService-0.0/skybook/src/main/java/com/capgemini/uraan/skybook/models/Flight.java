package com.capgemini.uraan.skybook.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Flight {
	@Id
	@Column(unique = true)
	@NotNull
	private String flightNo;
	
	@NotNull
	private String model ;
	
	
	@NotNull
	private int economySeat;
	
	@NotNull
	private int businessSeat;
	
	@NotNull
	private int cabinLuggage;
	
	@NotNull
	private int checkInLuggage;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "source_airport_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Airport sourceAirport;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "destination_airport_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Airport destinationAirport;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "airlines_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Airlines airlines;

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getEconomySeat() {
		return economySeat;
	}

	public void setEconomySeat(int economySeat) {
		this.economySeat = economySeat;
	}

	public int getBusinessSeat() {
		return businessSeat;
	}

	public void setBusinessSeat(int businessSeat) {
		this.businessSeat = businessSeat;
	}

	public int getCabinLuggage() {
		return cabinLuggage;
	}

	public void setCabinLuggage(int cabinLuggage) {
		this.cabinLuggage = cabinLuggage;
	}

	public int getCheckInLuggage() {
		return checkInLuggage;
	}

	public void setCheckInLuggage(int checkInLuggage) {
		this.checkInLuggage = checkInLuggage;
	}

	public Airport getSourceAirport() {
		return sourceAirport;
	}

	public void setSourceAirport(Airport sourceAirport) {
		this.sourceAirport = sourceAirport;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public Airlines getAirlines() {
		return airlines;
	}

	public void setAirlines(Airlines airlines) {
		this.airlines = airlines;
	}

}
