package com.nd.ms.authservice.utils;

import com.nd.ms.authservice.DTO.RolesDTO;
import com.nd.ms.authservice.DTO.UserDTO;
import com.nd.ms.authservice.model.Roles;
import com.nd.ms.authservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsersUtils {
  @Autowired
  private BCryptPasswordEncoder bcryptEncoder;
  
  public static UserDTO converToUserDTO(User user) {
    UserDTO userDTO = new UserDTO();
    if (user != null) {
      userDTO.setId(user.getId().longValue());
      userDTO.setCreated(user.getCreated());
      userDTO.setFirstName(user.getFirstName());
      userDTO.setLastName(user.getLastName());
      userDTO.setEmail(user.getEmail());
      userDTO.setEnabled(user.getEnabled());
      userDTO.setUserName(user.getUserName());
      if (user.getRoles() != null)
        for (int i = 0; i < user.getRoles().size(); i++) {
          RolesDTO role = new RolesDTO();
          role.setId(((Roles)user.getRoles().get(i)).getId());
          role.setRole(((Roles)user.getRoles().get(i)).getRoleName());
          userDTO.getRoles().add(role);
        }  
    } 
    return userDTO;
  }
  
  public static User converToUserEntity(UserDTO user) {
    User userEntity = new User();
    if (user != null) {
      userEntity.setId(Long.valueOf(user.getId()));
      userEntity.setCreated(user.getCreated());
      userEntity.setFirstName(user.getFirstName());
      userEntity.setLastName(user.getLastName());
      userEntity.setEmail(user.getEmail());
      userEntity.setEnabled(user.getEnabled());
      userEntity.setUserName(user.getUserName());
      userEntity.setPassword(user.getPassword());
      if (user.getRoles() != null)
        for (int i = 0; i < user.getRoles().size(); i++) {
          Roles role = new Roles();
          role.setId(((RolesDTO)user.getRoles().get(i)).getId());
          role.setRoleName(((RolesDTO)user.getRoles().get(i)).getRole());
          userEntity.getRoles().add(role);
        }  
    } 
    return userEntity;
  }
}
