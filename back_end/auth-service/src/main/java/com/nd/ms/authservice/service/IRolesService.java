package com.nd.ms.authservice.service;

import com.nd.ms.authservice.DTO.RolesDTO;
import java.util.List;

public interface IRolesService {
  List<RolesDTO> findAllRoles();
  
  RolesDTO createRoles(RolesDTO paramRolesDTO);
  
  void deleteRoles(long paramLong);
  
  RolesDTO findRoleById(long paramLong);
  
  RolesDTO findRoleByName(String paramString);
}
