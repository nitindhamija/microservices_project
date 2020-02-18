package com.nd.ms.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AuthServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(com.nd.ms.authservice.AuthServiceApplication.class, args);
  }
}
