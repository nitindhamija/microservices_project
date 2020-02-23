package com.nd.ms.webcrawler.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.nd.ms.webcrawler.model.CrawlRequest;
import com.nd.ms.webcrawler.model.CrawlResponse;
import com.nd.ms.webcrawler.model.Status;
import com.nd.ms.webcrawler.service.IWebCrawlerService;
@org.springframework.web.bind.annotation.RestController
//@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8081","http://10.194.29.146:8090"})
//@CrossOrigin(origins = {"http://mystdhamija.tk"})
@RequestMapping("/webcrawler")
public class WebCrawlerController {
	
	@Autowired
	private IWebCrawlerService webCrawlerService;

	@PostMapping("/process")
	public ResponseEntity<CrawlRequest> createCrawlRequest(@RequestParam String baseUrl,@RequestParam int depth ) {
		 CrawlRequest req=null;
		 boolean flag=webCrawlerService.checkIfExists(baseUrl, depth);
		if(depth>0 && !flag)
			{ 
			req = webCrawlerService.processCrawlRequest(new CrawlRequest(baseUrl,Status.SUBMITTED.toString(),depth));
			}
		
		if (req == null) {
		     return new ResponseEntity<CrawlRequest>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(builder.path("/todo?id={id}").buildAndExpand(todo.getId()).toUri());
		return new ResponseEntity<CrawlRequest>(req,headers, HttpStatus.CREATED);
	}
	
	
	@Scheduled(fixedDelay = 30000, initialDelay = 60000)
	public void ProcessCrawlRequest() {
		  
			 webCrawlerService.fetchAndProcessRequests();
			
	}
	
	@GetMapping("/get-url-response")
    public ResponseEntity<CrawlResponse> getCrawledResponse(@RequestParam("baseUrl") String BaseUrl) {
 	CrawlResponse res = webCrawlerService.fetchCrawlResponse(BaseUrl);
 	return new ResponseEntity<CrawlResponse>(res, HttpStatus.OK);
 }
	
	@GetMapping("/get-url-crawl-status")
    public ResponseEntity<CrawlRequest> getCrawledStatus(@RequestParam("baseUrl") String url) {
		CrawlRequest req = webCrawlerService.fetchCrawledUrlStatus(url);
 	return new ResponseEntity<CrawlRequest>(req, HttpStatus.OK);
 }

	@GetMapping("/info")
    public ResponseEntity<String> getAppStatus() {
 	System.out.println("web-crawler is up and running");
 	return new ResponseEntity<String>("auth-service is up and running", HttpStatus.OK);
 }
	
	@GetMapping("/health")
    public ResponseEntity<String> getHealthInfo() {
 	System.out.println("web-crawler health is good");
 	return new ResponseEntity<String>("auth-service health is good", HttpStatus.OK);
 }
	
}