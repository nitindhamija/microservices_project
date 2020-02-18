package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Todo;

public interface ITodoDAO {
	List<Todo> getTodoList();
	 //boolean createTodo(Todo todo);
	 Todo createTodo(Todo todo);
	  void deleteTodo(int todoId);
	  boolean todoExists(String name);
	  Todo updateTodo(int todoId,boolean flag);
	  void updateTodoAll(boolean flag);
}
