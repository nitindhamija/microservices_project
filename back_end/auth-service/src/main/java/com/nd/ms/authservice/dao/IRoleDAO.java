package com.nd.ms.authservice.dao;

import com.nd.ms.authservice.model.Roles;
import org.springframework.data.repository.CrudRepository;

public interface IRoleDAO extends CrudRepository<Roles, Long> {
  Roles findByRoleName(String paramString);
}
