//package com.example.demo.model;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import org.apache.commons.lang3.builder.EqualsBuilder;
//import org.apache.commons.lang3.builder.HashCodeBuilder;
//import org.apache.commons.lang3.builder.ToStringBuilder;
//
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//
//@Entity(name = "role")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
//public class Roles implements java.io.Serializable {
//    /**
//     * 
//     */
//    private static final long serialVersionUID = -5525359165179861924L;
//    @Id
//    @GeneratedValue(strategy=GenerationType.AUTO)
//    @Column(name = "id")
//    long id;
//    
//    @Column(name = "role_name")
//    private String roleName;
//    
////    @JsonIgnore
////    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
////    private List<User> users;
//    
//    public Roles() {
//        super();
//    }
//
//    public Roles(String roleName) {
//        super();
//        this.roleName = roleName;
//    }
//
//    public void setId(long id){
//        this.id = id;
//    }
//
//    public long getId(){
//        return id;
//    }
//
//  
//
//public String getRoleName() {
//		return roleName;
//	}
//
//	public void setRoleName(String roleName) {
//		this.roleName = roleName;
//	}
//
//	//    public List<User> getUsers() {
////        return users;
////    }
////
////    public void setUser(List<User> users) {
////        this.users = users;
////    }
//    @Override
//    public String toString() {
//        return ToStringBuilder.reflectionToString(this);
//    }
//
//    @Override
//    public int hashCode() {
//        return new HashCodeBuilder().append(id).append(roleName).
//        		//append(users).
//        		toHashCode();
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//        if ((other instanceof User) == false) {
//            return false;
//        }
//        Roles rhs = ((Roles) other);
//        return new EqualsBuilder().append(id, rhs.id).append(roleName, rhs.roleName).
//        		//append(users, rhs.users).
//        		isEquals();
//    }
//}
