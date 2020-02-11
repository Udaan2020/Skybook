package com.capgemini.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.BookingDao;
import com.capgemini.entity.Booking;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	private BookingDao dao;

	@Override
	public Optional<Booking> getBookingById(String bookingId) {
		
		return dao.findById(bookingId);
	}

	@Override
	public boolean cancelBooking(String bookingId) {
		dao.deleteById(bookingId);
		return true;
	}

}
