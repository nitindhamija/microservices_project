package com.nd.ms.webcrawler.dao;

import org.springframework.data.repository.CrudRepository;

import com.nd.ms.webcrawler.model.CrawlResponse;

public interface ICrawlerResponseDAO extends CrudRepository<CrawlResponse, Long> {
	CrawlResponse findByCrawlUrl(String url);
}
