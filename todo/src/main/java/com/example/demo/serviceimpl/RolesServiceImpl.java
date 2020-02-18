/*package com.example.demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.example.utils.RolesUtils;
import com.demo.example.utils.UsersUtils;
import com.example.demo.DTO.RolesDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.dao.IRoleDAO;
import com.example.demo.dao.IUserDAO;
import com.example.demo.model.Roles;
import com.example.demo.model.User;
import com.example.demo.service.IRolesService;
import com.example.demo.service.IUserService;

@Service
public class RolesServiceImpl implements IRolesService{

	@Autowired
	IRoleDAO rolesDOA;
	
	@Override
	public List<RolesDTO> findAllRoles() {
		// TODO Auto-generated method stub
		List<Roles> roles= (List<Roles>) rolesDOA.findAll();
		List<RolesDTO> rolesList=new ArrayList<>();
		roles.forEach(role->{
			
			rolesList.add(RolesUtils.converToRolesDTO(role));
		});
		return rolesList;
	}

	@Override
	public RolesDTO createRoles(RolesDTO role) {
		// TODO Auto-generated method stub
		Roles roleEntity=RolesUtils.converToRolesEntity(role); 
		roleEntity=rolesDOA.save(roleEntity);
		role=RolesUtils.converToRolesDTO(roleEntity);
		return role;
	}

	@Override
	public void deleteRoles(long roleId) {
		// TODO Auto-generated method stub
		rolesDOA.deleteById(roleId);
	}

//	@Override
//	public User updateRole(RolesDTO roleDTO) {
//		// TODO Auto-generated method stub
//		return rolesDOA.save(roleDTO);
//	}

	@Override
	public RolesDTO findRoleById(long roleId) {
		// TODO Auto-generated method stub
		Optional<Roles> roleEntity=rolesDOA.findById(roleId);
		RolesDTO role=RolesUtils.converToRolesDTO(roleEntity.get());
		return role;
	}

	@Override
	public RolesDTO findRoleByName(String roleName) {
		// TODO Auto-generated method stub
		Roles roleEntity=rolesDOA.findByRoleName(roleName);
		RolesDTO role=RolesUtils.converToRolesDTO(roleEntity);
		return role;
	}
}
*/