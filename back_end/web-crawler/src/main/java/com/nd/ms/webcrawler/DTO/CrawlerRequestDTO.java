/*package com.nd.ms.webcrawler.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.nd.ms.webcrawler.DTO.CrawlerResponseDTO;
import com.nd.ms.webcrawler.model.Details;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "crawlUrl", "totalLinks", "totalImages", "details"})
public class CrawlerRequestDTO {
  
  @JsonIgnore
  @JsonProperty("id")
  @JsonPropertyDescription("The unique identifier for a user")
  private long id;
  @JsonIgnore
  @JsonProperty("crawlUrl")
  private Date crawlUrl;
  
  @JsonProperty("totalLinks")
  private String totalLinks;
  
  @JsonProperty("totalImages")
  private String totalImages;
  
  @JsonProperty("email")
  private String email;
  
  @JsonProperty("userName")
  private String userName;
  
  @JsonProperty("password")
  private String password;
  
  @JsonProperty("enabled")
  private int enabled;
  
  @JsonProperty("details")
  private List<Details> details = new ArrayList<>();
  
  @JsonProperty("id")
  public long getId() {
    return this.id;
  }
  
  @JsonProperty("id")
  public void setId(long id) {
    this.id = id;
  }
  
  @JsonProperty("created")
  public Date getCreated() {
    return this.created;
  }
  
  @JsonProperty("created")
  public void setCreated(Date created) {
    this.created = created;
  }
  
  @JsonProperty("firstName")
  public String getFirstName() {
    return this.firstName;
  }
  
  @JsonProperty("firstName")
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  @JsonProperty("lastName")
  public String getLastName() {
    return this.lastName;
  }
  
  @JsonProperty("lastName")
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  @JsonProperty("email")
  public String getEmail() {
    return this.email;
  }
  
  @JsonProperty("email")
  public void setEmail(String email) {
    this.email = email;
  }
  
  @JsonProperty("userName")
  public String getUserName() {
    return this.userName;
  }
  
  @JsonProperty("userName")
  public void setUserName(String userName) {
    this.userName = userName;
  }
  
  @JsonProperty("enabled")
  public int getEnabled() {
    return this.enabled;
  }
  
  @JsonProperty("enabled")
  public void setEnabled(int enabled) {
    this.enabled = enabled;
  }
  
  @JsonProperty("password")
  public String getPassword() {
    return this.password;
  }
  
  @JsonProperty("password")
  public void setPassword(String password) {
    this.password = password;
  }
  
  @JsonProperty("roles")
  public List<CrawlerResponseDTO> getRoles() {
    return this.roles;
  }
  
  @JsonProperty("roles")
  public void setRoles(List<CrawlerResponseDTO> roles) {
    this.roles = roles;
  }
  
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
  
  public int hashCode() {
    return (new HashCodeBuilder()).append(this.id).append(this.created).append(this.firstName).append(this.lastName).append(this.email).append(this.userName).append(this.enabled).append(this.roles).toHashCode();
  }
  
  public boolean equals(Object other) {
    if (other == this)
      return true; 
    if (!(other instanceof com.nd.ms.webcrawler.DTO.CrawlerRequestDTO))
      return false; 
    com.nd.ms.webcrawler.DTO.CrawlerRequestDTO rhs = (com.nd.ms.webcrawler.DTO.CrawlerRequestDTO)other;
    return (new EqualsBuilder()).append(this.id, rhs.id).append(this.created, rhs.created).append(this.firstName, rhs.firstName).append(this.lastName, rhs.lastName).append(this.email, rhs.email).append(this.userName, rhs.userName).append(this.enabled, rhs.enabled).append(this.roles, rhs.roles).isEquals();
  }
}
*/