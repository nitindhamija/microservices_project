package com.nd.ms.authservice.serviceimpl;

import com.nd.ms.authservice.DTO.RolesDTO;
import com.nd.ms.authservice.dao.IRoleDAO;
import com.nd.ms.authservice.model.Roles;
import com.nd.ms.authservice.service.IRolesService;
import com.nd.ms.authservice.utils.RolesUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesServiceImpl implements IRolesService {
  @Autowired
  IRoleDAO rolesDOA;
  
  public List<RolesDTO> findAllRoles() {
    List<Roles> roles = (List<Roles>)this.rolesDOA.findAll();
    List<RolesDTO> rolesList = new ArrayList<>();
    roles.forEach(role -> rolesList.add(RolesUtils.converToRolesDTO(role)));
    return rolesList;
  }
  
  public RolesDTO createRoles(RolesDTO role) {
    Roles roleEntity = RolesUtils.converToRolesEntity(role);
    roleEntity = (Roles)this.rolesDOA.save(roleEntity);
    role = RolesUtils.converToRolesDTO(roleEntity);
    return role;
  }
  
  public void deleteRoles(long roleId) {
    this.rolesDOA.deleteById(Long.valueOf(roleId));
  }
  
  public RolesDTO findRoleById(long roleId) {
    Optional<Roles> roleEntity = this.rolesDOA.findById(Long.valueOf(roleId));
    RolesDTO role = RolesUtils.converToRolesDTO(roleEntity.get());
    return role;
  }
  
  public RolesDTO findRoleByName(String roleName) {
    Roles roleEntity = this.rolesDOA.findByRoleName(roleName);
    RolesDTO role = RolesUtils.converToRolesDTO(roleEntity);
    return role;
  }
}
