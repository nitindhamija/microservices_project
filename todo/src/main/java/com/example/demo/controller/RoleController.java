/*package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.DTO.RolesDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.service.IRolesService;
import com.example.demo.service.IUserService;
@org.springframework.web.bind.annotation.RestController
//@CrossOrigin(origins = {"http://localhost:4200","http://13.232.77.74:8090"})
@CrossOrigin(origins = {"http://mystdhamija.tk"})
public class RoleController {
	
	@Autowired
	private IRolesService rolesService;
	public final String APP_NAME="role";
 @RequestMapping("/todos")
 public String greet() {
  return "Hello from the other side!!!";
 }
	@GetMapping(APP_NAME+"/roles")
    public ResponseEntity<List<RolesDTO>> getRoleList() {
 	List<RolesDTO> list = rolesService.findAllRoles();
 	return new ResponseEntity<List<RolesDTO>>(list, HttpStatus.OK);
 }
	
	@PostMapping(APP_NAME+"/roles")
	public ResponseEntity<RolesDTO> createTodo(@RequestBody RolesDTO rolesDTO) {
		rolesDTO = rolesService.createRoles(rolesDTO);
		if (rolesDTO == null) {
		     return new ResponseEntity<RolesDTO>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(builder.path("/todo?id={id}").buildAndExpand(todo.getId()).toUri());
		return new ResponseEntity<RolesDTO>(rolesDTO,headers, HttpStatus.CREATED);
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