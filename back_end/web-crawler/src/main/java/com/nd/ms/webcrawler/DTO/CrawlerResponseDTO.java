package com.nd.ms.webcrawler.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "role"})
public class CrawlerResponseDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @JsonProperty("id")
  @JsonPropertyDescription("The unique identifier for a user")
  private long id;
  
  @JsonProperty("role")
  private String role;
  
  @JsonProperty("id")
  public long getId() {
    return this.id;
  }
  
  @JsonProperty("id")
  public void setId(long id) {
    this.id = id;
  }
  
  @JsonProperty("role")
  public String getRole() {
    return this.role;
  }
  
  @JsonProperty("role")
  public void setRole(String role) {
    this.role = role;
  }
  
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
  
  public int hashCode() {
    return (new HashCodeBuilder()).append(this.id).append(this.role).toHashCode();
  }
  
  public boolean equals(Object other) {
    if (other == this)
      return true; 
    if (!(other instanceof com.nd.ms.webcrawler.DTO.CrawlerResponseDTO))
      return false; 
    com.nd.ms.webcrawler.DTO.CrawlerResponseDTO rhs = (com.nd.ms.webcrawler.DTO.CrawlerResponseDTO)other;
    return (new EqualsBuilder()).append(this.id, rhs.id).append(this.role, rhs.role).isEquals();
  }
}
