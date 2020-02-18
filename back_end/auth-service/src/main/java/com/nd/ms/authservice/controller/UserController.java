package com.nd.ms.authservice.controller;

import com.nd.ms.authservice.DTO.UserDTO;
import com.nd.ms.authservice.service.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://mystdhamija.tk"})
public class UserController {
  @Autowired
  private IUserService userService;
  
  public final String APP_NAME = "user";
  
  @GetMapping({"user/users"})
  public ResponseEntity<List<UserDTO>> getUserList() {
    List<UserDTO> list = this.userService.findAllUser();
    return new ResponseEntity(list, HttpStatus.OK);
  }
  
  @PostMapping({"/signup"})
  public ResponseEntity<UserDTO> createTodo(@RequestBody UserDTO userDTO) {
    userDTO = this.userService.createUser(userDTO);
    if (userDTO == null)
      return new ResponseEntity(HttpStatus.CONFLICT); 
    HttpHeaders headers = new HttpHeaders();
    return new ResponseEntity(userDTO, (MultiValueMap)headers, HttpStatus.CREATED);
  }
}
