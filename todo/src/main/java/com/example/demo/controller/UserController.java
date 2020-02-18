/*package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.DTO.UserDTO;
import com.example.demo.model.Todo;
import com.example.demo.model.User;
import com.example.demo.service.IUserService;
@org.springframework.web.bind.annotation.RestController
//@CrossOrigin(origins = {"http://localhost:4200","http://13.232.77.74:8090"})
@CrossOrigin(origins = {"http://mystdhamija.tk"})
public class UserController {
	
	@Autowired
	private IUserService userService;
	public final String APP_NAME="user";
 @RequestMapping("/todos")
 public String greet() {
  return "Hello from the other side!!!";
 }
	@GetMapping(APP_NAME+"/users")
    public ResponseEntity<List<UserDTO>> getUserList() {
 	List<UserDTO> list = userService.findAllUser();
 	return new ResponseEntity<List<UserDTO>>(list, HttpStatus.OK);
 }
	
	@PostMapping("/signup")
	public ResponseEntity<UserDTO> createTodo(@RequestBody UserDTO userDTO) {
		userDTO = userService.createUser(userDTO);
		if (userDTO == null) {
		     return new ResponseEntity<UserDTO>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(builder.path("/todo?id={id}").buildAndExpand(todo.getId()).toUri());
		return new ResponseEntity<UserDTO>(userDTO,headers, HttpStatus.CREATED);
	}
//	
//	@PutMapping(APP_NAME+"/todos")
//	public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo) {
//		userService.updateTodo(todo.getId(),todo.isCompleted());
//		return new ResponseEntity<Todo>(todo,HttpStatus.OK);
//	}
//	
//	@PutMapping(APP_NAME+"/todosall")
//	public ResponseEntity<Void> updateTodoAll(@RequestParam("checkAll") Boolean checkAll) {
//		userService.updateTodoAll(checkAll);
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
//	
//	@DeleteMapping(APP_NAME+"/todos")
//	public ResponseEntity<Void> deleteTodo(@RequestParam("id") int id) {
//		userService.deleteTodo(id);
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
 
}*/