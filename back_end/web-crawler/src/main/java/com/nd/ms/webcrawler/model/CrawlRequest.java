package com.nd.ms.webcrawler.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Crawl_Request")
public class CrawlRequest implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "req_id", unique = true, nullable = false)
	private Long reqId;
	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	private static final long serialVersionUID = 1L;

	

	@Column(name = "base_Url")
	private String baseUrl;

	@Column(name = "depth")
	private int depth;

	@Column(name = "status", length = 50)
	private String status;


	public Long getReqId() {
		return reqId;
	}

	public void setReqId(Long reqId) {
		this.reqId = reqId;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CrawlRequest(String baseUrl, String status, int depth) {
		this.baseUrl = baseUrl;
		this.status = status;
		this.depth = depth;

	}

	public CrawlRequest(String baseUrl) {
		this.baseUrl = baseUrl;
		this.status = Status.SUBMITTED.toString();
		this.depth = 1;

	}
	public CrawlRequest() {
		super();

	}
}