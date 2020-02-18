package com.nd.ms.authservice.dao;

import com.nd.ms.authservice.model.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserDAO extends CrudRepository<User, Long> {
  User findByUserName(String paramString);
}
