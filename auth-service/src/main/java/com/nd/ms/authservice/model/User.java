package com.nd.ms.authservice.model;

import com.nd.ms.authservice.model.Roles;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {
  private static final long serialVersionUID = 4910225916550731448L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;
  
  @Column(name = "created")
  Date created;
  
  @Column(name = "firstName", length = 100)
  private String firstName;
  
  @Column(name = "lastName", length = 100)
  private String lastName;
  
  @Column(name = "email", length = 150)
  private String email;
  
  @Column(name = "username", length = 150)
  private String userName;
  
  @Column(name = "enabled")
  private int enabled;
  
  @Column
  private String password;
  
  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
  @JoinTable(joinColumns = {@JoinColumn(name = "userId", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "id")})
  private List<Roles> roles;
  
  public Long getId() {
    return this.id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public Date getCreated() {
    return this.created;
  }
  
  public void setCreated(Date created) {
    this.created = created;
  }
  
  public String getFirstName() {
    return this.firstName;
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public String getLastName() {
    return this.lastName;
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  public String getEmail() {
    return this.email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getUserName() {
    return this.userName;
  }
  
  public void setUserName(String userName) {
    this.userName = userName;
  }
  
  public int getEnabled() {
    return this.enabled;
  }
  
  public void setEnabled(int enabled) {
    this.enabled = enabled;
  }
  
  public String getPassword() {
    return this.password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public User() {
    this.roles = new ArrayList<>();
  }
  
  public List<Roles> getRoles() {
    return this.roles;
  }
  
  public void setRoles(List<Roles> roles) {
    this.roles = roles;
  }
  
  public User(String firstName, String lastName, String email, String username, List<Roles> roles) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.userName = username;
    this.roles = roles;
  }
}
