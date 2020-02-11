package com.capgemini.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Booking {
 private String name;
 private String userId;
 private String email;
 private long number;
 
 @Id
 private String bookingId;
 private String date;
 private String departure;
 private String arrival;
 public String getDeparture() {
	return departure;
}

public void setDeparture(String departure) {
	this.departure = departure;
}

public String getArrival() {
	return arrival;
}

public void setArrival(String arrival) {
	this.arrival = arrival;
}

private String departureTime;
 private String cabinClass;
 private int numberOfPassengers;
 private String flightStatus;
 
 public Booking() {
	// TODO Auto-generated constructor stub
}

public Booking(String name, String userId, String email, long number, String bookingId, String date, String from,
		String to, String departureTime, String cabinClass, int numberOfPassengers, String flightStatus) {
	super();
	this.name = name;
	this.userId = userId;
	this.email = email;
	this.number = number;
	this.bookingId = bookingId;
	this.date = date;
	this.departureTime = departureTime;
	this.cabinClass = cabinClass;
	this.numberOfPassengers = numberOfPassengers;
	this.flightStatus = flightStatus;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getUserId() {
	return userId;
}

public void setUserId(String userId) {
	this.userId = userId;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public long getNumber() {
	return number;
}

public void setNumber(long number) {
	this.number = number;
}

public String getBookingId() {
	return bookingId;
}

public void setBookingId(String bookingId) {
	this.bookingId = bookingId;
}

public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}

public String getDepartureTime() {
	return departureTime;
}

public void setDepartureTime(String departureTime) {
	this.departureTime = departureTime;
}

public String getCabinClass() {
	return cabinClass;
}

public void setCabinClass(String cabinClass) {
	this.cabinClass = cabinClass;
}

public int getNumberOfPassengers() {
	return numberOfPassengers;
}

public void setNumberOfPassengers(int numberOfPassengers) {
	this.numberOfPassengers = numberOfPassengers;
}

public String getFlightStatus() {
	return flightStatus;
}

public void setFlightStatus(String flightStatus) {
	this.flightStatus = flightStatus;
}
 
}
