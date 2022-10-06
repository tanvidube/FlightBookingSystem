package com.flights.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "flight")
public class Flight {
	
	@Id //Indicates that the member field below is the primary key of current entity
	private Integer flightId;
	private String flightName;
	private String flightFrom;
	private String flightTo;
	private String date;
	private double fare;
	public Integer getFlightId() {
		return flightId;
	}
	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public String getFlightFrom() {
		return flightFrom;
	}
	public void setFlightFrom(String flightFrom) {
		this.flightFrom = flightFrom;
	}
	public String getFlightTo() {
		return flightTo;
	}
	public void setFlightTo(String flightTo) {
		this.flightTo = flightTo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	
	//Constructor is used to create a object of an entity or a class
	public Flight(Integer flightId, String flightName, String flightFrom, String flightTo, String date, double fare) {
		super();
		this.flightId = flightId;
		this.flightName = flightName;
		this.flightFrom = flightFrom;
		this.flightTo = flightTo;
		this.date = date;
		this.fare = fare;
	}
	
	@Override 
	public String toString() {  //toString method is used for string representation of object
		return "Flight [flightId=" + flightId + ", flightName=" + flightName + ", flightFrom=" + flightFrom
				+ ", flightTo=" + flightTo + ", date=" + date + ", fare=" + fare + "]";
	}
	
	

}
