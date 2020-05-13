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


@Table(name="sciencefield")
@Entity
public class ScienceField { 
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
        
	@Column(name="name")
	private String name;
        

	public ScienceField() {}

  	public long getId(){
    	return id;
  	}
  
  	public void setId(long id){
       	this.id = id;	
	}
      
  	public String getName(){
    	return name;
  	}
  
  	public void setName(String name){
       	this.name = name;	
	}
      

}