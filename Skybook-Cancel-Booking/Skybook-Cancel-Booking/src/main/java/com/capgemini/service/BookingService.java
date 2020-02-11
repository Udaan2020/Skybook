package com.capgemini.service;


import java.util.Optional;

import com.capgemini.entity.Booking;


public interface BookingService {

 Optional<Booking> getBookingById(String bookingId);
 
 boolean cancelBooking(String bookingId);
}
