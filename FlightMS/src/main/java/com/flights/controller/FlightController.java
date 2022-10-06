package com.flights.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flights.Exception.BusinessException;
import com.flights.Exception.ControllerException;
import com.flights.entity.Flight;
import com.flights.service.FlightService;



@RestController //This annotation is used to handle the REST APIs , create RESTfull Web services and handle client Request
public class FlightController {
	
	Logger logger = LoggerFactory.getLogger(FlightController.class);
	@Autowired
	private FlightService flightser;
	
//	@GetMapping 
//	public String show() {
//	return "Its working";
//	}
	
	@PostMapping("/add") 
	public ResponseEntity<?> addFlight(@RequestBody Flight flight) { //RequestBody bind method parameter with Http/Web reponse
		logger.info("To add new flight");
		try {
		Flight flightsaved = flightser.addflight(flight);
		logger.info("flight added successfully");
		return new ResponseEntity<Flight>(flightsaved, HttpStatus.CREATED);
	}catch (BusinessException be) {
		ControllerException ce = new ControllerException(be.getErrorCode() , be.getErrorMessage());
		return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
	}catch (Exception e) {
		ControllerException ce = new ControllerException("609" ,"Something went wrong" );
		return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
	}
	}
	
	@PutMapping("/update")
	public String updateFlight(@RequestBody Flight flight) {
		flightser.updateflight(flight);
		return "Flight got updated";
	}
	
	@DeleteMapping("/delete")
	public String deleteflight(@RequestBody Flight flight) {
		flightser.deleteflight(flight);
		return "Flight deleted successfully";
		}
	
	@DeleteMapping("/delete/{id}")
    public String deleteflightById(@PathVariable("id") Integer flightId) { //PathVariable is extract the value from URL
		flightser.deleteflightById(flightId);
		return "Flight deleted successfully";
		}
	
	@DeleteMapping("/delete/{name}")
	public String deleteflightByName(@PathVariable("Name") String flightName) {
		flightser.deleteFlightByName(flightName);
		return "Flight deleted successfully";
		}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?> findByFlightId(@PathVariable("id") Integer flightId){
		List<Flight> lst = (List<Flight>) flightser.findFlightById(flightId);
		if(lst.size()==0) {
			return new ResponseEntity<>("No flight available with" + flightId , HttpStatus.OK);
		}
		
		else {
			
		return new ResponseEntity<>(lst , HttpStatus.OK);
		}
	}
		
		@GetMapping("/find/{name}")
		public ResponseEntity<?> findByFlightName(@PathVariable("name") String flightName){
			List<Flight> lst = (List<Flight>) flightser.findByFlightName(flightName);
			if(lst.size()==0) {
				return new ResponseEntity<>("No flight available with" + flightName , HttpStatus.OK);
			}
			
			else {
				return new ResponseEntity<>(lst , HttpStatus.OK);
			}
		
		}
		
		@GetMapping("/showflight/{date}")
		public ResponseEntity<?> findByDate(@PathVariable("date") String date){
			List<Flight> lst = (List<Flight>) flightser.findByDate(date);
			if(lst.size()==0) {
				return new ResponseEntity<>("No flight available with" + date , HttpStatus.OK);
			}
			
			else {
				
				return new ResponseEntity<>(lst , HttpStatus.OK);
			}
		}
		
		@GetMapping("/show flight/{from}/{to}")
	public ResponseEntity<?> getflight(@PathVariable String from , @PathVariable String to ){
		List<Flight> lst = (List<Flight>) flightser.findByFlightFromAndFlightTo(from , to);
		logger.info("Fligths got added in list");
		if(lst.size()==0) {
			logger.info("No flights found");
			return new ResponseEntity<>("No flight available with" + from + "to" + to , HttpStatus.OK);
		}
		
		else {
			logger.info("Flights are available");
			return new ResponseEntity<>(lst , HttpStatus.OK);
			}
		}
		
		
		@GetMapping("/showflight/{from}/{to}/{date}")
		public ResponseEntity<?> getflight(@PathVariable String from , @PathVariable String to , @PathVariable String date){
			List<Flight> lst = (List<Flight>) flightser.getFlight(from , to , date);
			if(lst.size()==0) {
				return new ResponseEntity<>("No flight available with" + from + "to" + to + "date" + date , HttpStatus.OK);
			}
			
			else {
				return new ResponseEntity<>(lst , HttpStatus.OK);
			}
			
		}
}
			
	
	
	


