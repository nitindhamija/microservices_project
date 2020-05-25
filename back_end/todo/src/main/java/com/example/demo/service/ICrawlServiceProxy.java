package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.config.FiegnReqConfig;
import com.example.demo.config.HystrixClientFallbackFactory;


//@FeignClient(name="web-crawler", configuration = FiegnReqConfig.class)
@FeignClient(name="web-crawler", configuration = FiegnReqConfig.class, fallbackFactory = HystrixClientFallbackFactory.class)
//@RibbonClient(name="forex-service")
public interface ICrawlServiceProxy {

	@PostMapping("/webcrawler/process")
	public ResponseEntity<Object> createCrawlRequest(@RequestParam String baseUrl,@RequestParam int depth );

}