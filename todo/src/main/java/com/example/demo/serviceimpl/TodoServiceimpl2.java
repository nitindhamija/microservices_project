package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Todo;
import com.example.demo.service.ITodoService;
@Service
public class TodoServiceimpl2 implements ITodoService {

	@Override
	public List<Todo> getTodoList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Todo createTodo(Todo todo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTodo(int todoId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Todo updateTodo(int todoId, boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTodoAll(boolean flag) {
		// TODO Auto-generated method stub

	}

}
