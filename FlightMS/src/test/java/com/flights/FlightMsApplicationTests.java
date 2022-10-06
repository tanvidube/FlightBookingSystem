package com.flights;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.flights.entity.Flight;
import com.flights.repository.FlightRepo;
import com.flights.service.FlightService;

@RunWith(SpringRunner.class)
@SpringBootTest
class FlightMsApplicationTests {
	
	@Autowired
	private FlightService  flightser;
	
	@MockBean
	private FlightRepo flightrepo;

	@Test
	public void addflightTest(){
		Flight temp = new Flight (101 , "AirIndia" , "Bhopal" , "Delhi" , "03-09-2022" , 2000.5);
		when(flightrepo.insert(temp)).thenReturn(temp);
		assertEquals(temp, flightser.addflight(temp));
	}
	
	@Test
	public void deleteflightTest() {
		Flight temp = new Flight (102 , "SpiceJet" , "Pune" , "Lucknow" , "06/09/2022" , 3500.5);
		flightser.deleteflight(temp);
		verify(flightrepo , times(1)).delete(temp);
		}
	
	@Test
	public void deleteflightByIdTest() {
		Flight temp = new Flight (101 , "AirIndia" , "Bhopal" , "Delhi" , "03-09-2022" , 2000.5);
		flightser.deleteflightById(temp.getFlightId());
		verify(flightrepo , times(1)).deleteById(101);
		}
	
	@Test
	public void getFlightTest() {
		List<Flight> lst = new ArrayList<>();
		Flight temp = new Flight (103 , "Etihad Airways" , "Mumbai" , "Dubai" , "06/09/2022" , 5000.5);
		lst.add(temp);
		when(flightrepo.findAll()).thenReturn(lst);
		assertEquals(lst, flightser.getFlight("Mumbai", "Dubai", "06/09/2022"));
	}
	
	@Test
	public void updateflightTest() {
		Flight temp = new  Flight (101 , "AirIndia" , "Bhopal" , "Delhi" , "03-09-2022" , 2000.5);
		when(flightrepo.save(temp)).thenReturn(temp);
		assertEquals(temp, flightser.updateflight(temp));
	}

	@Test
	public void findflightById() {
		List<Flight> lst = new ArrayList<>();
		Flight temp = new Flight (103 , "Etihad Airways" , "Mumbai" , "Dubai" , "06/09/2022" , 5000.5);
		lst.add(temp);
		when(flightrepo.findByFlightId(temp.getFlightId())).thenReturn(lst);
    	assertEquals(lst, flightser.findFlightById(103));
		
	}
	
	@Test
	public void findflightByName() {
		java.util.List<Flight> lst = new ArrayList<>();
		Flight temp = new Flight (103 , "Etihad Airways" , "Mumbai" , "Dubai" , "06/09/2022" , 5000.5);
		lst.add(temp);
		when(flightrepo.findByFlightName(temp.getFlightName())).thenReturn(lst);
    	assertEquals(lst, flightser.findByFlightName("Etihad Airways"));
		
	}
	
	@Test
	public void findflightByDate() {
		java.util.List<Flight> lst = new ArrayList<>();
		Flight temp = new Flight (103 , "Etihad Airways" , "Mumbai" , "Dubai" , "06/09/2022" , 5000.5);
		lst.add(temp);
		when(flightrepo.findByDate(temp.getDate())).thenReturn(lst);
    	assertEquals(lst, flightser.findByDate("06/09/2022"));
		
	}

	@Test	
	public void findByFlightFromAndFlightToTest() {
		List<Flight> lst = new ArrayList<>();
		Flight temp = new Flight (103 , "Etihad Airways" , "Mumbai" , "Dubai" , "06/09/2022" , 5000.5);
		lst.add(temp);
		when(flightrepo.findByFlightFromAndFlightTo("Mumbai" , "Dubai")).thenReturn(lst);
    	assertEquals(lst, flightser.findByFlightFromAndFlightTo("Mumbai" , "Dubai"));
		}
	
	@Test
	public void deleteFlightByName() {
		Flight temp = new Flight (101 , "AirIndia" , "Bhopal" , "Delhi" , "03-09-2022" , 2000.5);
		flightser.deleteFlightByName(temp.getFlightName());
		verify(flightrepo , times(1)).deleteByFlightName("AirIndia");
	}
		
}
