package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Todo;
import com.example.demo.service.ICrawlServiceProxy;
import com.example.demo.service.ITodoService;
@RestController
//@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8081","http://10.194.29.146:8090"})
@CrossOrigin
public class TodoController {
	
	@Autowired
	@Qualifier("todoServiceImpl")
	private ITodoService todoService;
	
	@Autowired
	private ICrawlServiceProxy proxy;
	
	public final String APP_NAME="todo";
 /*@RequestMapping("/todos")
 public String greet() {
  return "Hello from the other side!!!";
 }*/
	@GetMapping(APP_NAME+"/todos")
    public ResponseEntity<List<Todo>> getTodoList() {
 	List<Todo> list = todoService.getTodoList();
 	return new ResponseEntity<List<Todo>>(list, HttpStatus.OK);
 }
	
	@PostMapping(APP_NAME+"/todos")
	public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
		 todo = todoService.createTodo(todo);
		if (todo == null) {
		     return new ResponseEntity<Todo>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(builder.path("/todo?id={id}").buildAndExpand(todo.getId()).toUri());
		return new ResponseEntity<Todo>(todo,headers, HttpStatus.CREATED);
	}
	
	@PutMapping(APP_NAME+"/todos")
	public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo) {
		todoService.updateTodo(todo.getId(),todo.isCompleted());
		return new ResponseEntity<Todo>(todo,HttpStatus.OK);
	}
	
	@PutMapping(APP_NAME+"/todosall")
	public ResponseEntity<Void> updateTodoAll(@RequestParam("checkAll") Boolean checkAll) {
		todoService.updateTodoAll(checkAll);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping(APP_NAME+"/todos")
	public ResponseEntity<Void> deleteTodo(@RequestParam("id") int id) {
		todoService.deleteTodo(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(APP_NAME+"/submit-crawl-req")
	public ResponseEntity<Todo> submitCrawlRequest(@RequestBody Todo todo) {
		 //todo = todoService.createTodo(todo);
		proxy.createCrawlRequest(todo.getName(), 2);
		if (todo == null) {
		     return new ResponseEntity<Todo>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(builder.path("/todo?id={id}").buildAndExpand(todo.getId()).toUri());
		return new ResponseEntity<Todo>(todo,headers, HttpStatus.CREATED);
	}
	
	@GetMapping("/info")
    public ResponseEntity<String> getAppStatus() {
 	System.out.println("auth-service is up and running");
 	return new ResponseEntity<String>("auth-service is up and running", HttpStatus.OK);
 }
	
	@GetMapping("/health")
    public ResponseEntity<String> getHealthInfo() {
 	System.out.println("auth-service health is good");
 	return new ResponseEntity<String>("auth-service health is good", HttpStatus.OK);
 }
	
}