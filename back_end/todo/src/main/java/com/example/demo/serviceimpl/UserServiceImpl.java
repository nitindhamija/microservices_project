/*package com.example.demo.serviceimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.example.utils.UsersUtils;
import com.example.demo.DTO.UserDTO;
import com.example.demo.dao.IUserDAO;
import com.example.demo.model.User;
import com.example.demo.service.IUserService;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService,IUserService{

	@Autowired
	IUserDAO userDAO;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Override
	public List<UserDTO> findAllUser() {
		// TODO Auto-generated method stub
		List<User> users= (List<User>) userDAO.findAll();
		List<UserDTO> userList=new ArrayList<>();
		users.forEach(user->{
			
			userList.add(UsersUtils.converToUserDTO(user));
		});
		return userList;
	}

	@Override
	public UserDTO createUser(UserDTO user) {
		// TODO Auto-generated method stub
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		User userEntity=UsersUtils.converToUserEntity(user); 
		userEntity=userDAO.save(userEntity);
		user=UsersUtils.converToUserDTO(userEntity);
		return user;
	}

	@Override
	public void deleteUser(long userId) {
		// TODO Auto-generated method stub
		userDAO.deleteById(userId);
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return userDAO.save(user);
	}

	@Override
	public Optional<User> findUserById(long userId) {
		// TODO Auto-generated method stub
		return userDAO.findById(userId);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userDAO.findByUserName(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthority(user));
	}
	
	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			//authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

}
*/