package com.nd.ms.authservice.serviceimpl;

import com.nd.ms.authservice.DTO.UserDTO;
import com.nd.ms.authservice.dao.IUserDAO;
import com.nd.ms.authservice.model.Roles;
import com.nd.ms.authservice.model.User;
import com.nd.ms.authservice.service.IUserService;
import com.nd.ms.authservice.utils.UsersUtils;
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

@Service("userService")
public class UserServiceImpl implements UserDetailsService, IUserService {
  @Autowired
  IUserDAO userDAO;
  
  @Autowired
  private BCryptPasswordEncoder bcryptEncoder;
  
  public List<UserDTO> findAllUser() {
    List<User> users = (List<User>)this.userDAO.findAll();
    List<UserDTO> userList = new ArrayList<>();
    users.forEach(user -> userList.add(UsersUtils.converToUserDTO(user)));
    return userList;
  }
  
  public UserDTO createUser(UserDTO user) {
    user.setPassword(this.bcryptEncoder.encode(user.getPassword()));
    User userEntity = UsersUtils.converToUserEntity(user);
    userEntity = (User)this.userDAO.save(userEntity);
    user = UsersUtils.converToUserDTO(userEntity);
    return user;
  }
  
  public void deleteUser(long userId) {
    this.userDAO.deleteById(Long.valueOf(userId));
  }
  
  public User updateUser(User user) {
    return (User)this.userDAO.save(user);
  }
  
  public Optional<User> findUserById(long userId) {
    return this.userDAO.findById(Long.valueOf(userId));
  }
  
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.userDAO.findByUserName(username);
    if (user == null)
      throw new UsernameNotFoundException("Invalid username or password."); 
    return (UserDetails)new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthority(user));
  }
  
  private Set<SimpleGrantedAuthority> getAuthority(User user) {
    Set<SimpleGrantedAuthority> authorities = new HashSet<>();
    user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName())));
    return authorities;
  }
}
