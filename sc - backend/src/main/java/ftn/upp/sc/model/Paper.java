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


@Table(name="paper")
@Entity
public class Paper { 
 
	@Id
	private String doi;
        
	@ManyToOne(fetch=FetchType.LAZY)
	private Edition edition;
        
	@OneToOne
	private Application paperDetails;
        

	public Paper() {}

  	public String getDoi(){
    	return doi;
  	}
  
  	public void setDoi(String doi){
       	this.doi = doi;	
	}
      
  	public Edition getEdition(){
    	return edition;
  	}
  
  	public void setEdition(Edition edition){
       	this.edition = edition;	
	}
      
  	public Application getPaperDetails(){
    	return paperDetails;
  	}
  
  	public void setPaperDetails(Application paperDetails){
       	this.paperDetails = paperDetails;	
	}
      

}