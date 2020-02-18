package com.example.demo.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
 
/**
 * @author itsvenkis
 *
 */
public class Flight {
  
 private String flightMaker;
 private String flightType;
 private int flightAge;
  
 public int getFlightAge() {
  return flightAge;
 }
  
 @XmlElement
 public void setFlightAge(int flightAge) {
  this.flightAge = flightAge;
 }
 @XmlAttribute
 public String getFlightMaker() {
  return flightMaker;
 }
 public void setFlightMaker(String flightMaker) {
  this.flightMaker = flightMaker;
 }
  
 @XmlAttribute
 public String getFlightType() {
  return flightType;
 }
 public void setFlightType(String flightType) {
  this.flightType = flightType;
 }
}