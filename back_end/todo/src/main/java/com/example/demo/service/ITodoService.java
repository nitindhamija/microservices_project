package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Todo;

public interface ITodoService {
	 List<Todo> getTodoList();
	 //boolean createTodo(Todo todo);
	 Todo createTodo(Todo todo);
   //  void updateTodo(Todo todo);
     void deleteTodo(int todoId);
     Todo updateTodo(int todoId,boolean flag);
     void updateTodoAll(boolean flag);
}
