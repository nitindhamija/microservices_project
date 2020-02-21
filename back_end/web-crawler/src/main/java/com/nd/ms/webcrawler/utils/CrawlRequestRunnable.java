package com.nd.ms.webcrawler.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nd.ms.webcrawler.dao.ICrawlerResponseDAO;
import com.nd.ms.webcrawler.dao.IWebCrawlerDAO;
import com.nd.ms.webcrawler.model.CrawlResponse;
import com.nd.ms.webcrawler.model.Details;
import com.nd.ms.webcrawler.model.Status;

@Component
public class CrawlRequestRunnable implements Runnable {

	@Autowired
	private ICrawlerResponseDAO crawlerResponseDAO;
	
	@Autowired
	private IWebCrawlerDAO webCrawlerDAO;
	
	private final String base_url;

	private final int depth;

	// private int depth_counter = 0;

	private static int total_links = 0;
	private static int total_images = 0;

	private HashSet<String> links;
	private List<Details> detailList;

	public CrawlRequestRunnable(String base_url, int depth) {
		super();
		this.base_url = base_url;
		this.depth = depth;
		this.links = new HashSet<>();
		this.detailList = new ArrayList<Details>();
	}
	public CrawlRequestRunnable() {
		super();
		this.base_url = "";
		this.depth = 0;
		this.links = new HashSet<>();
		this.detailList = new ArrayList<Details>();
	}


	@Override
	public void run() {

		getPageLinks(this.base_url, depth);
		System.out.println("saving the record ");
		try {
		CrawlResponse crawlRespone=crawlerResponseDAO.save(new CrawlResponse(this.base_url, links.size(), total_images, detailList));
		System.out.println("saving the record ");
		
		webCrawlerDAO.updateCrawlRequest(base_url,Status.Processed.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void getPageLinks(String URL, int depth) {
		if ((!links.contains(URL) && (depth > 0))) {
			System.out.println(">> Depth: " + depth + " [" + URL + "]");
			try {
				links.add(URL);

				Document document = Jsoup.connect(URL).get();
				if (!URL.equalsIgnoreCase(this.base_url)) {
					String title = document.title();
					int imageCount = document.select("img").size();
					total_images += imageCount;
					detailList.add(new Details(title, URL, imageCount));
				}
				Elements linksOnPage = document.select("a[href]");
				depth--;
				total_links++;
				for (Element page : linksOnPage) {
					getPageLinks(page.attr("abs:href"), depth);
				}
			} catch (IOException e) {
				System.out.println("For nitin '" + URL + "': " + e.getMessage());
				webCrawlerDAO.updateCrawlRequest(base_url,Status.Failed.toString());
			}
		}
	}

}
