package com.flights.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flights.Exception.BusinessException;
import com.flights.entity.Flight;
import com.flights.repository.FlightRepo;

@Service //is with classes where we write our main business logic
public class FlightService {
	
	@Autowired //its enables us to inject the object dependency implicitly
	private FlightRepo flightrepo;
	
	public Flight addflight(Flight flight) {
		
		try {
			if(flight.getFlightName().isEmpty() || flight.getFlightName().length()==0 
					|| flight.getFlightId() == null
					|| flight.getFlightFrom().isEmpty() || flight.getFlightFrom().length() == 0
					|| flight.getFlightTo().isEmpty() || flight.getFlightTo().length() == 0
					|| flight.getDate().isEmpty() || flight.getDate().length() ==0 
					|| flight.getFare() == 0) {
				throw new BusinessException("601", "Filght name can not be null or empty , Please give a proper flight name");
				}
				return flightrepo.insert(flight);
		}catch (IllegalArgumentException e) {
			throw new BusinessException("602", "Flight cannot be null" + e.getMessage());
		}catch (Exception e) {
			throw new BusinessException("603", "Something went wrong" + e.getMessage());
		}
		
	}
	
//	private Exception BusinessException(String string, String string2) {
		// TODO Auto-generated method stub
//		return null;
//	}

	public Flight updateflight(Flight flight) {
		return flightrepo.save(flight);
			
	}
	
	
	
	public void deleteflight(Flight flight) {
		 flightrepo.delete(flight);
		 
	}
	
	public void deleteflightById(Integer Id) {
		 flightrepo.deleteById(Id);
		 
	}
	
	public void deleteFlightByName(String flightName) {
		flightrepo.deleteByFlightName(flightName);
	}
	
	public List<Flight> findFlightById(Integer flightId) {
		
		try {
			return flightrepo.findByFlightId(flightId);
		
			
		}catch (IllegalArgumentException e) {
			throw new BusinessException("606", "their is no flight to show" + e.getMessage());
		
	}catch (java.util.NoSuchElementException e) {
		throw new BusinessException("607", " Given flightId is null , please send some Id to fetch flight " + e.getMessage());
	    }
    }
		
		
	public List<Flight> findByFlightName(String flightName){
		return flightrepo.findByFlightName(flightName);
	}
	
	public List<Flight> findByDate(String date) {
		return flightrepo.findByDate(date);
	}
	
	public List<Flight> findByFlightFromAndFlightTo(String flightFrom , String flightTo){
	List<Flight> lst = flightrepo.findByFlightFromAndFlightTo(flightFrom , flightTo);
	return lst;
		
		
	}
	
	
	public List<Flight> getFlight(String flightFrom , String flightTo , String date ){
		try {
			
		List<Flight> lst = flightrepo.findAll();
		
		if(flightFrom.isEmpty() || flightTo.isEmpty() || date.isEmpty()) {
			throw new BusinessException("604", "This List is empty , their is no data available");
		}
		return lst.stream().filter(flight->(flight.getFlightFrom().equals(flightFrom)&&flight.getFlightTo().equals(flightTo)&&flight.getDate().equals(date)))
		.toList();
		}catch (Exception e) {
			throw new BusinessException("605", "Something went wrong in service layer while fetching the list" + e.getMessage());
		}
	}
		 
		 
	
	

}
