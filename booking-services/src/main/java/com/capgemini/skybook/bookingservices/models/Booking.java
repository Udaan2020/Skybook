package com.capgemini.skybook.bookingservices.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Booking {

	@Id
	@Column(unique = true)
	private String flightPnr;

	@NotNull
	private String flightId;

	@NotNull
	private String userId;

	@NotNull
	private String transactionId;

	@NotNull
	private String fare;

	public String getFlightPnr() {
		return flightPnr;
	}

	public void setFlightPnr(String flightPnr) {
		this.flightPnr = flightPnr;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getFare() {
		return fare;
	}

	public void setFare(String fare) {
		this.fare = fare;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
