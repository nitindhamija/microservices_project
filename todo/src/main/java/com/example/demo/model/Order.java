package com.example.demo.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
 
/**
 * @author itsvenkis
 *
 */
@XmlRootElement(name ="order")
public class Order {
  
 private String flightNumber;
 private String orderId;
 private String source;
 private String destination;
 private List<Flight> flights;
 

 
 public List<Flight> getFlights() {
	return flights;
}

 @XmlElementWrapper
 @XmlElement(name="flight")
public void setFlights(List<Flight> flights) {
	this.flights = flights;
}

public String getFlightNumber() {
  return flightNumber;
 }
  
 @XmlElement
 public void setFlightNumber(String flightNumber) {
  this.flightNumber = flightNumber;
 }
 public String getOrderId() {
  return orderId;
 }
 @XmlElement
 public void setOrderId(String orderId) {
  this.orderId = orderId;
 }
 public String getSource() {
  return source;
 }
 @XmlElement
 public void setSource(String source) {
  this.source = source;
 }
 public String getDestination() {
  return destination;
 }
 @XmlElement
 public void setDestination(String destination) {
  this.destination = destination;
 }
}