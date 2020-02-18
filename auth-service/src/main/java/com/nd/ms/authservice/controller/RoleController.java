package com.nd.ms.authservice.controller;

import com.nd.ms.authservice.DTO.RolesDTO;
import com.nd.ms.authservice.service.IRolesService;
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
public class RoleController {
  @Autowired
  private IRolesService rolesService;
  
  public final String APP_NAME = "role";
  
  @GetMapping({"role/roles"})
  public ResponseEntity<List<RolesDTO>> getRoleList() {
    List<RolesDTO> list = this.rolesService.findAllRoles();
    return new ResponseEntity(list, HttpStatus.OK);
  }
  
  @PostMapping({"role/roles"})
  public ResponseEntity<RolesDTO> createTodo(@RequestBody RolesDTO rolesDTO) {
    rolesDTO = this.rolesService.createRoles(rolesDTO);
    if (rolesDTO == null)
      return new ResponseEntity(HttpStatus.CONFLICT); 
    HttpHeaders headers = new HttpHeaders();
    return new ResponseEntity(rolesDTO, (MultiValueMap)headers, HttpStatus.CREATED);
  }
}
