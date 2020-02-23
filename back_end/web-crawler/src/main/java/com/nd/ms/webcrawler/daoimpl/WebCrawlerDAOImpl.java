package com.nd.ms.webcrawler.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.nd.ms.webcrawler.dao.IWebCrawlerDAO;
import com.nd.ms.webcrawler.model.CrawlRequest;
import com.nd.ms.webcrawler.model.CrawlResponse;
import com.nd.ms.webcrawler.model.Details;
import com.nd.ms.webcrawler.model.Status;
@Repository
public class WebCrawlerDAOImpl implements IWebCrawlerDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	private final String INSERT_SQL = "insert into Crawl_Request (base_Url,depth,status) values(?,?,?)";
	private final String INSERT_SQL_RESPONSE = "insert into Crawl_Response (crawl_url,total_Links,total_Images,details) values(?,?,?,?)";
	
	class TodoRowMapper implements RowMapper<CrawlRequest> {
		@Override
		public CrawlRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
			CrawlRequest req = new CrawlRequest();
			req.setReqId(rs.getLong("req_id"));
			req.setBaseUrl(rs.getString("base_Url"));
			req.setDepth(rs.getInt("depth"));
			req.setStatus(rs.getString("status"));
			return req;
		}
	}

	

	public CrawlRequest submitCrawlRequest(final CrawlRequest req) {
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, req.getBaseUrl());
				ps.setInt(2, req.getDepth());
				ps.setString(3, req.getStatus());
				return ps;
			}
		}, holder);

		long newReqId = holder.getKey().longValue();
		req.setReqId(newReqId);
		return fetchTodo(newReqId).get(0);
		
	}

	public List<CrawlRequest> fetchTodo(long newReqId) {
		return jdbcTemplate.query("select * from Crawl_Request where req_id=?", new Object[] { newReqId },new TodoRowMapper());
	}

	@Override
	public List<CrawlRequest> fetchUnProcessedRequests() {
		// TODO Auto-generated method stub
		List<CrawlRequest> req=jdbcTemplate.query("select * from Crawl_Request where status=?", new Object[] { Status.SUBMITTED.toString() },new TodoRowMapper());
		jdbcTemplate.update("update Crawl_Request set status=? where status=?", new Object[] {Status.InProcess.toString(),Status.SUBMITTED.toString() });
		return req;
	}

	@Override
	public void updateCrawlRequest(String base_url,String status ) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("update Crawl_Request set status=? where base_url=?", new Object[] {status, base_url});	
	}



}
