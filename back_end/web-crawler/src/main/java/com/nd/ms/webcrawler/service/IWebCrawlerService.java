package com.nd.ms.webcrawler.service;

import java.util.List;

import com.nd.ms.webcrawler.model.CrawlRequest;

public interface IWebCrawlerService {
	/* List<CrawlRequest> getTodoList();
	 //boolean createTodo(Todo todo);
	 CrawlRequest createTodo(CrawlRequest todo);
   //  void updateTodo(Todo todo);
     void deleteTodo(int todoId);
     CrawlRequest updateTodo(int todoId,boolean flag);
     void updateTodoAll(boolean flag);*/
	public CrawlRequest processCrawlRequest(CrawlRequest crawlRequest);
	public List<CrawlRequest> fetchAndProcessRequests();
}
