package com.nd.ms.webcrawler.serviceimpl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nd.ms.webcrawler.dao.IWebCrawlerDAO;
import com.nd.ms.webcrawler.model.CrawlRequest;
import com.nd.ms.webcrawler.service.IWebCrawlerService;
import com.nd.ms.webcrawler.utils.CrawlRequestRunnable;

@Service
public class WebCrawlerServiceImpl implements IWebCrawlerService {
	@Autowired
	private IWebCrawlerDAO webCrawlerDAO;

	
	
	/*@Override
	public List<CrawlRequest> getTodoList() {
		// TODO Auto-generated method stub
		return todoDao.getTodoList();
	}
*/

	@Override
	public CrawlRequest processCrawlRequest(CrawlRequest req) {
		// TODO Auto-generated method stub
		 req = webCrawlerDAO.submitCrawlRequest(req);
         return req;
	}


	@Override
	public List<CrawlRequest> fetchAndProcessRequests() {
		// TODO Auto-generated method stub
		List<CrawlRequest> requestList=webCrawlerDAO.fetchUnProcessedRequests();
		processAndPersistRequestList(requestList);
		return null;
	}

public void processAndPersistRequestList(List<CrawlRequest> reqList) {
	ExecutorService executor = Executors.newFixedThreadPool(5);
	reqList.forEach(req->{
		Runnable runnable=new CrawlRequestRunnable(req.getBaseUrl(),req.getDepth());
		Future<?> taskStatus=	executor.submit(runnable);
	});
	executor.shutdown();
    while (!executor.isTerminated()) {
    }
}
	

/*
	@Override
	public void deleteTodo(int todoId) {
		todoDao.deleteTodo(todoId);
		
	}


	@Override
	public CrawlRequest updateTodo(int todoId, boolean flag) {
		// TODO Auto-generated method stub
		return todoDao.updateTodo(todoId, flag);
	}


	@Override
	public void updateTodoAll(boolean flag) {
		// TODO Auto-generated method stub
		 todoDao.updateTodoAll(flag);
	}



*/

	

	
	
	
}
