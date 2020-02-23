package com.nd.ms.webcrawler.service;

import java.util.List;

import com.nd.ms.webcrawler.model.CrawlRequest;
import com.nd.ms.webcrawler.model.CrawlResponse;

public interface IWebCrawlerService {
	
	public CrawlRequest processCrawlRequest(CrawlRequest crawlRequest);
	public List<CrawlRequest> fetchAndProcessRequests();
	public CrawlResponse fetchCrawlResponse(String url);
	public CrawlRequest fetchCrawledUrlStatus(String url);
	public boolean checkIfExists(String url,int depth);
}
