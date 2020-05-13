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


@Table(name="userdetails")
@Entity
public class UserDetails { 
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
        
	@Column(name="email")
	private String email;
        
	@Column(name="firstname")
	private String firstName;
        
	@Column(name="lastname")
	private String lastName;
        
	@Column(name="city")
	private String city;
        
	@Column(name="country")
	private String country;
        

	public UserDetails() {}

  	public long getId(){
    	return id;
  	}
  
  	public void setId(long id){
       	this.id = id;	
	}
      
  	public String getEmail(){
    	return email;
  	}
  
  	public void setEmail(String email){
       	this.email = email;	
	}
      
  	public String getFirstName(){
    	return firstName;
  	}
  
  	public void setFirstName(String firstName){
       	this.firstName = firstName;	
	}
      
  	public String getLastName(){
    	return lastName;
  	}
  
  	public void setLastName(String lastName){
       	this.lastName = lastName;	
	}
      
  	public String getCity(){
    	return city;
  	}
  
  	public void setCity(String city){
       	this.city = city;	
	}
      
  	public String getCountry(){
    	return country;
  	}
  
  	public void setCountry(String country){
       	this.country = country;	
	}
      

}