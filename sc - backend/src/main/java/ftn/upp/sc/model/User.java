package ftn.upp.sc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Table(name="user")
@Entity
public class User { 
 
	@Id
	private String username;
        
	@Column(name="password")
	private String password;
        
	@OneToOne
	private UserDetails userDetails;
        
	@JsonIgnore
	private int failedLoginAttempts = 0;
        

	public User() {}

  	public String getUsername(){
    	return username;
  	}
  
  	public void setUsername(String username){
       	this.username = username;	
	}
      
  	public String getPassword(){
    	return password;
  	}
  
  	public void setPassword(String password){
       	this.password = password;	
	}
      
  	public UserDetails getUserDetails(){
    	return userDetails;
  	}
  
  	public void setUserDetails(UserDetails userDetails){
       	this.userDetails = userDetails;	
	}
      
	public int getFailedLoginAttempts() {
		return failedLoginAttempts;
	}


	public void setFailedLoginAttempts(int failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}


	public void incrementFailedLoginAttempts() {
		++this.failedLoginAttempts;
	}

}