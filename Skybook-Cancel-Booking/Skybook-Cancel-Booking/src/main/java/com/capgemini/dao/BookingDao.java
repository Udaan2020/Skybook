package com.capgemini.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entity.Booking;

@Repository
public interface BookingDao extends JpaRepository<Booking, String>{

}
