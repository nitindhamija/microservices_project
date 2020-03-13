package com.example.demo.daoimpl;

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

import com.example.demo.dao.ITodoDAO;
import com.example.demo.model.Todo;
@Repository
public class TodoDAOImpl implements ITodoDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	private final String INSERT_SQL = "insert into todolist (name) values(?)";
	
	class TodoRowMapper implements RowMapper<Todo> {
		@Override
		public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
			Todo todo = new Todo();
			todo.setId(rs.getInt("id"));
			todo.setName(rs.getString("name"));
			todo.setCompleted(rs.getBoolean("completed"));
			return todo;
		}
	}

	@Override
	public List<Todo> getTodoList() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from todolist", new TodoRowMapper());
	}

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
	

	public Todo createTodo(final Todo todo) {
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL,  new String [] {"id"});
				ps.setString(1, todo.getName());
				return ps;
			}
		}, holder);
		
		int newTodoId = holder.getKey().intValue();
		todo.setId(newTodoId);
		return fetchTodo(newTodoId).get(0);
		
	}

	public List<Todo> fetchTodo(int todoId) {
		return jdbcTemplate.query("select * from todolist where id=?", new Object[] { todoId },new TodoRowMapper());
	}
	@Override
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
	public Todo updateTodo(int todoId, boolean flag) {
		//if(flag)
		jdbcTemplate.update("update todolist set completed=? where id=?", new Object[] {flag, todoId });
		return fetchTodo(todoId).get(0);
		/*else
		jdbcTemplate.update("update todolist set completed=false where id=?", new Object[] { todoId });	*/
		
	}

	@Override
	public void updateTodoAll(boolean flag) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("update todolist set completed=? ", new Object[] {flag });
	}

}
