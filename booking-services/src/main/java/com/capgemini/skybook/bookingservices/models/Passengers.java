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

public class Passengers {
	@Id
	@Column(unique = true)
	@NotNull
	private String passengerId;

	@NotNull
	private String bookingId;

	@NotNull
	private String paasengerName;

	@NotNull
	private String passengerEmail;

	@NotNull
	private String passengerNumber;

	@NotNull
	private String passengerAdhaar;

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}

	public String getPaasengerName() {
		return paasengerName;
	}

	public void setPaasengerName(String paasengerName) {
		this.paasengerName = paasengerName;
	}

	public String getPassengerEmail() {
		return passengerEmail;
	}

	public void setPassengerEmail(String passengerEmail) {
		this.passengerEmail = passengerEmail;
	}

	public String getPassengerNumber() {
		return passengerNumber;
	}

	public void setPassengerNumber(String passengerNumber) {
		this.passengerNumber = passengerNumber;
	}

	public String getPassengerAdhaar() {
		return passengerAdhaar;
	}

	public void setPassengerAdhaar(String passengerAdhaar) {
		this.passengerAdhaar = passengerAdhaar;
	}

}
