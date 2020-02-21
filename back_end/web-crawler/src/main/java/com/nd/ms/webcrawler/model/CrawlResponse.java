package com.nd.ms.webcrawler.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Crawl_Response")
public class CrawlResponse {

	public String getCrawlUrl() {
		return crawlUrl;
	}

	public void setCrawlUrl(String crawlUrl) {
		this.crawlUrl = crawlUrl;
	}

	public void setDetails(List<Details> details) {
		this.details = details;
	}

	public CrawlResponse(String crawlUrl, int totalLinks, int totalImages, List<Details> details) {
		super();
		this.crawlUrl = crawlUrl;
		this.totalLinks = totalLinks;
		this.totalImages = totalImages;
		this.details = details;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "crawl_url")
	private String crawlUrl;

	@Column(name = "total_links")
	private int totalLinks;

	@Column(name = "total_images")
	private int totalImages;

	@OneToMany(mappedBy = "crawlResponse")
	List<Details> details;

	public int getTotalLinks() {
		return totalLinks;
	}

	public void setTotalLinks(int totalLinks) {
		this.totalLinks = totalLinks;
	}

	public int getTotalImages() {
		return totalImages;
	}

	public void setTotalImages(int totalImages) {
		this.totalImages = totalImages;
	}

	public List getDetails() {
		return details;
	}


}
