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


@Table(name="magazine")
@Entity
public class Magazine { 
 
	@Id
	private String issn;
        
	@Column(name="name")
	private String name;
        
	@OneToOne
	private Editor mainEditor;
        
    @ManyToMany(fetch=FetchType.LAZY)
	private List<ScienceField> scienceFields = new ArrayList<ScienceField>();
		
        
	@Column(name="paymenttype")
	private MagazinePaymentType paymentType;
        

	public Magazine() {}

  	public String getIssn(){
    	return issn;
  	}
  
  	public void setIssn(String issn){
       	this.issn = issn;	
	}
      
  	public String getName(){
    	return name;
  	}
  
  	public void setName(String name){
       	this.name = name;	
	}
      
  	public Editor getMainEditor(){
    	return mainEditor;
  	}
  
  	public void setMainEditor(Editor mainEditor){
       	this.mainEditor = mainEditor;	
	}
      
   	public void addScienceFields(ScienceField sciencefield){
		this.scienceFields.add(sciencefield);
	}
	
	public void removeScienceFields(ScienceField sciencefield){
		scienceFields.remove(sciencefield);
	}
	
	public List<ScienceField> getScienceFields(){
     	return scienceFields;
    }
      
   	public void setScienceFields( List<ScienceField> scienceFields){
     	this.scienceFields = scienceFields;
   	}
	
  	public MagazinePaymentType getPaymentType(){
    	return paymentType;
  	}
  
  	public void setPaymentType(MagazinePaymentType paymentType){
       	this.paymentType = paymentType;	
	}
      

}