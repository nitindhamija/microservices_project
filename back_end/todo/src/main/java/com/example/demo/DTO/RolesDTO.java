package com.example.demo.DTO;


import javax.persistence.Entity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
 
    "id",
    "role"
   
})
public class RolesDTO implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 
     */
	  @JsonProperty("id")
	    @JsonPropertyDescription("The unique identifier for a user")
	    private long id;
	    @JsonProperty("role")
	    private String role;
	  
	
	    


	
	    @JsonProperty("id")
	    public long getId() {
	        return id;
	    }
	    
	    @JsonProperty("id")
	    public void setId(long id) {
			this.id = id;
		}



	    @JsonProperty("role")
	    public String getRole() {
			return role;
		}
	    @JsonProperty("role")
		public void setRole(String role) {
			this.role = role;
		}

		@Override
	    public String toString() {
	        return ToStringBuilder.reflectionToString(this);
	    }
	  
	    @Override
	    public int hashCode() {
	        return new HashCodeBuilder().append(id).append(role).toHashCode();
	    }

	    @Override
	    public boolean equals(Object other) {
	        if (other == this) {
	            return true;
	        }
	        if ((other instanceof UserDTO) == false) {
	            return false;
	        }
	        RolesDTO rhs = ((RolesDTO) other);
	        return new EqualsBuilder().append(id, rhs.id).append(role, rhs.role).isEquals();
	    }
}
