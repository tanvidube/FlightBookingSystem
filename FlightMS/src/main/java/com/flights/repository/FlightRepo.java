package com.flights.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.flights.entity.Flight;

@Repository //It is used to indicate that the class provide the mechanism of CRUD and storage
public interface FlightRepo extends MongoRepository<Flight, Integer> {
	
	public List<Flight> findByFlightId(Integer flightId);
	public List<Flight> findByFlightName(String flightName);
	public List<Flight> findByDate(String date);
	public List<Flight> findByFlightFromAndFlightTo(String flightFrom , String flightTo);
	public void deleteByFlightName(String flightName);
}
