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
	/*
	@Override
	public List<CrawlRequest> getTodoList() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from todolist", new TodoRowMapper());
	}*/

	@Override
//	public boolean createTodo(Todo todo) {
//		// TODO Auto-generated method stub
//		if (todoExists(todo.getName())) {
//			return false;
//		} else {
//			int id=jdbcTemplate.update("insert into todolist ( name) " + "values(  ?)",
//					new Object[] {  todo.getName() });
//			
//			return true;
//		}
//	}
	

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
/*	@Override
	public void deleteTodo(int todoId) {
		jdbcTemplate.update("delete from todolist where id=?", new Object[] { todoId });
	}

	@Override
	public boolean todoExists(String name) {
		String sql = "SELECT count(*) FROM todolist WHERE name = ?";
		int count = jdbcTemplate.queryForObject(sql, new Object[] { name }, Integer.class);
		return count > 0 ? true : false;
	}

	@Override
	public CrawlRequest updateTodo(int todoId, boolean flag) {
		//if(flag)
		jdbcTemplate.update("update todolist set completed=? where id=?", new Object[] {flag, todoId });
		return fetchTodo(todoId).get(0);
		else
		jdbcTemplate.update("update todolist set completed=false where id=?", new Object[] { todoId });	
		
	}

	@Override
	public void updateTodoAll(boolean flag) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("update todolist set completed=? ", new Object[] {flag });
	}*/

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

/*	@Override
	public CrawlResponse persistCrawledRequestToDb(CrawlResponse res) {
		// TODO Auto-generated method stub
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL_RESPONSE, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, res.getCrawlUrl());
				ps.setInt(2, res.getTotalLinks());
				ps.setInt(2, res.getTotalImages());
				ps.seta(3, res.getDetails());
				return ps;
			}
		}, holder);

		long newReqId = holder.getKey().longValue();
		res.setReqId(newReqId);
		return fetchTodo(newReqId).get(0);
		
	}*/

}
