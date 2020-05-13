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


@Table(name="edition")
@Entity
public class Edition { 
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
        
	@Column(name="editionname")
	private String editionName;
        
	@ManyToOne(fetch=FetchType.LAZY)
	private Magazine magazine;
        

	public Edition() {}

  	public long getId(){
    	return id;
  	}
  
  	public void setId(long id){
       	this.id = id;	
	}
      
  	public String getEditionName(){
    	return editionName;
  	}
  
  	public void setEditionName(String editionName){
       	this.editionName = editionName;	
	}
      
  	public Magazine getMagazine(){
    	return magazine;
  	}
  
  	public void setMagazine(Magazine magazine){
       	this.magazine = magazine;	
	}
      

}