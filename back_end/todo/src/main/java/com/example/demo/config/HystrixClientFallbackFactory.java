package com.example.demo.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.service.ICrawlServiceProxy;


import feign.hystrix.FallbackFactory;

@Component
public class HystrixClientFallbackFactory implements FallbackFactory<ICrawlServiceProxy> {

	@Override
	public ICrawlServiceProxy create(Throwable arg0) {
		return new ICrawlServiceProxy() {

			@Override
			public ResponseEntity<Object> createCrawlRequest(String baseUrl, int depth) {
				// TODO Auto-generated method stub
				System.out.println("fallback called "+arg0.getMessage());
				arg0.printStackTrace();
				return new ResponseEntity<Object>(null,null, HttpStatus.NOT_FOUND);
			}
		
		};
	}



}
