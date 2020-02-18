package com.nd.ms.authservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity(name = "role")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Roles implements Serializable {
  private static final long serialVersionUID = -5525359165179861924L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  long id;
  
  @Column(name = "role_name")
  private String roleName;
  
  public Roles() {}
  
  public Roles(String roleName) {
    this.roleName = roleName;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  public long getId() {
    return this.id;
  }
  
  public String getRoleName() {
    return this.roleName;
  }
  
  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }
  
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
  
  public int hashCode() {
    return (new HashCodeBuilder()).append(this.id).append(this.roleName)
      
      .toHashCode();
  }
  
  public boolean equals(Object other) {
    if (other == this)
      return true; 
    if (!(other instanceof com.nd.ms.authservice.model.User))
      return false; 
    com.nd.ms.authservice.model.Roles rhs = (com.nd.ms.authservice.model.Roles)other;
    return (new EqualsBuilder()).append(this.id, rhs.id).append(this.roleName, rhs.roleName)
      
      .isEquals();
  }
}
