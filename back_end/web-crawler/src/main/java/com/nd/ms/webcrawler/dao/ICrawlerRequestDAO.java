package com.nd.ms.webcrawler.dao;

import org.springframework.data.repository.CrudRepository;

import com.nd.ms.webcrawler.model.CrawlRequest;



public interface ICrawlerRequestDAO extends CrudRepository<CrawlRequest, Long> {
	CrawlRequest findByBaseUrl(String url);
	CrawlRequest findByBaseUrlAndDepth(String url,int depth);
}
