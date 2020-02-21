package com.nd.ms.webcrawler.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Details")
public class Details implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "details_id", unique = true, nullable = false)
	private Long detailsId;

	@Column(name = "pageTitle")
	private String pageTitle;

	@Column(name = "pageLink")
	private String pageLink;

	@Column(name = "imageCount")
	private int imageCount;

	//@ManyToOne
	//@JoinColumn(name = "id", nullable = false)
	//private CrawlResponse crawlResponse;




	public Long getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(Long detailsId) {
		this.detailsId = detailsId;
	}

/*	public CrawlResponse getCrawlResponse() {
		return crawlResponse;
	}

	public void setCrawlResponse(CrawlResponse crawlResponse) {
		this.crawlResponse = crawlResponse;
	}*/

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getPageLink() {
		return pageLink;
	}

	public void setPageLink(String pageLink) {
		this.pageLink = pageLink;
	}

	public long getImageCount() {
		return imageCount;
	}

	public void setImageCount(int imageCount) {
		this.imageCount = imageCount;
	}
public Details(String pageTitle, String pageLink,int imgCount) {
	this.pageTitle=pageTitle;
	this.pageLink=pageLink;
	this.imageCount=imgCount;
	
}
}
