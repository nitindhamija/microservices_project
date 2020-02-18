package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.example.demo.dao.ITodoDAO;
import com.example.demo.daoimpl.TodoDAOImpl;
import com.example.demo.service.ITodoService;
import com.example.demo.serviceimpl.TodoServiceImpl;
@EnableDiscoveryClient
@SpringBootApplication
public class TodoApplication {

	@Bean
	 public ITodoService ITodoService() {
	      return new TodoServiceImpl();
	   }
	@Bean
	 public ITodoDAO ITodoDAO() {
	      return new TodoDAOImpl();
	   }
	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}
}
