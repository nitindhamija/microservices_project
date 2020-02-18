package com.nd.ms.authservice.service;

import com.nd.ms.authservice.DTO.UserDTO;
import com.nd.ms.authservice.model.User;
import java.util.List;
import java.util.Optional;

public interface IUserService {
  List<UserDTO> findAllUser();
  
  UserDTO createUser(UserDTO paramUserDTO);
  
  void deleteUser(long paramLong);
  
  User updateUser(User paramUser);
  
  Optional<User> findUserById(long paramLong);
}
