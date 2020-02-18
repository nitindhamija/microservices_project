package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ITodoDAO;
import com.example.demo.model.Todo;
import com.example.demo.service.ITodoService;

@Service
public class TodoServiceImpl implements ITodoService {
	@Autowired
	@Qualifier("todoDAOImpl")
	private ITodoDAO todoDao;

	
	@Override
	public List<Todo> getTodoList() {
		// TODO Auto-generated method stub
		return todoDao.getTodoList();
	}


	@Override
	public Todo createTodo(Todo todo) {
		// TODO Auto-generated method stub
		todo=todoDao.createTodo(todo);
         return todo;
	}


	@Override
	public void deleteTodo(int todoId) {
		todoDao.deleteTodo(todoId);
		
	}


	@Override
	public Todo updateTodo(int todoId, boolean flag) {
		// TODO Auto-generated method stub
		return todoDao.updateTodo(todoId, flag);
	}


	@Override
	public void updateTodoAll(boolean flag) {
		// TODO Auto-generated method stub
		 todoDao.updateTodoAll(flag);
	}





	

	
	
	
}
