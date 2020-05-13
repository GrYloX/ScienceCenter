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


@Table(name="editor")
@Entity
public class Editor { 
 
	@OneToOne
	private Magazine magazine;
        
	@ManyToOne(fetch=FetchType.LAZY)
	private ScienceField scienceField;
        
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
        
	@Column(name="title")
	private String title;
        
	@OneToOne
	private User user;
        

	public Editor() {}

  	public Magazine getMagazine(){
    	return magazine;
  	}
  
  	public void setMagazine(Magazine magazine){
       	this.magazine = magazine;	
	}
      
  	public ScienceField getScienceField(){
    	return scienceField;
  	}
  
  	public void setScienceField(ScienceField scienceField){
       	this.scienceField = scienceField;	
	}
      
  	public Integer getId(){
    	return id;
  	}
  
  	public void setId(Integer id){
       	this.id = id;	
	}
      
  	public String getTitle(){
    	return title;
  	}
  
  	public void setTitle(String title){
       	this.title = title;	
	}
      
  	public User getUser(){
    	return user;
  	}
  
  	public void setUser(User user){
       	this.user = user;	
	}
      

}