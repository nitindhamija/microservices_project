package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Flight;
import com.example.demo.model.Order;
import com.example.demo.model.Todo;

@RestController
@RequestMapping("/getOrder")
public class OrderController {

	@RequestMapping(method = RequestMethod.GET,produces = { "application/xml" })
	 public Order getOrder(){
	  Order order = new Order();
	  order.setDestination("Mumbai");
	  order.setFlightNumber("f238");
	  order.setOrderId("OR908765");
	 List flights=new ArrayList<Flight>();
	  Flight flight = new Flight();
	  flight.setFlightMaker("BOEING");
	  flight.setFlightType("COMMERCIAL");
	  flight.setFlightAge(300);
	  flights.add(flight);
	  order.setFlights(flights);
	  return order;
	 }
	  
	@RequestMapping(method = RequestMethod.POST, consumes = { "application/xml" },produces = { "application/xml" })
	 public ResponseEntity<Order> postOrder(@RequestBody Order order){
	                //Its crime to use System.out.println . Use loggers instead
	  System.out.println(order.getDestination());
	  //return Response.status(200).entity("Received XML").build();
	 // return new ResponseEntity<Todo>(todo,headers, HttpStatus.CREATED);
	  return new ResponseEntity<Order>( order, HttpStatus.OK);
	 }
	
}
