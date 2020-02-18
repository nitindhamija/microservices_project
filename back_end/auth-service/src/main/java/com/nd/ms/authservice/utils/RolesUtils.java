package com.nd.ms.authservice.utils;

import com.nd.ms.authservice.DTO.RolesDTO;
import com.nd.ms.authservice.model.Roles;

public class RolesUtils {
  public static RolesDTO converToRolesDTO(Roles role) {
    RolesDTO rolesDTO = new RolesDTO();
    if (role != null) {
      rolesDTO.setId(role.getId());
      rolesDTO.setRole(role.getRoleName());
    } 
    return rolesDTO;
  }
  
  public static Roles converToRolesEntity(RolesDTO roles) {
    Roles rolesEntity = new Roles();
    if (roles != null) {
      rolesEntity.setId(roles.getId());
      rolesEntity.setRoleName(roles.getRole());
    } 
    return rolesEntity;
  }
}
