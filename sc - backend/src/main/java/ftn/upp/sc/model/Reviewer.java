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


@Table(name="reviewer")
@Entity
public class Reviewer { 
 
    @ManyToMany(fetch=FetchType.LAZY)
	private List<Magazine> magazines = new ArrayList<Magazine>();
		
        
    @ManyToMany(fetch=FetchType.LAZY)
	private List<ScienceField> fields = new ArrayList<ScienceField>();
		
        
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
        
	@Column(name="title")
	private String title;
        
	@OneToOne
	private User user;
        

	public Reviewer() {}

   	public void addMagazines(Magazine magazine){
		this.magazines.add(magazine);
	}
	
	public void removeMagazines(Magazine magazine){
		magazines.remove(magazine);
	}
	
	public List<Magazine> getMagazines(){
     	return magazines;
    }
      
   	public void setMagazines( List<Magazine> magazines){
     	this.magazines = magazines;
   	}
	
   	public void addFields(ScienceField sciencefield){
		this.fields.add(sciencefield);
	}
	
	public void removeFields(ScienceField sciencefield){
		fields.remove(sciencefield);
	}
	
	public List<ScienceField> getFields(){
     	return fields;
    }
      
   	public void setFields( List<ScienceField> fields){
     	this.fields = fields;
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