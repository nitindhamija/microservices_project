package com.nd.ms.webcrawler.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nd.ms.webcrawler.model.CrawlRequest;
import com.nd.ms.webcrawler.model.CrawlResponse;
import com.nd.ms.webcrawler.model.Status;

public interface IWebCrawlerDAO  {
/*	List<CrawlRequest> getTodoList();
	 //boolean createTodo(Todo todo);
	 CrawlRequest createTodo(CrawlRequest todo);
	  void deleteTodo(int todoId);
	  boolean todoExists(String name);
	  CrawlRequest updateTodo(int todoId,boolean flag);
	  void updateTodoAll(boolean flag);*/
	public CrawlRequest submitCrawlRequest(CrawlRequest todo);
	public List<CrawlRequest> fetchUnProcessedRequests();
	public void updateCrawlRequest(String base_url,String status);
}
