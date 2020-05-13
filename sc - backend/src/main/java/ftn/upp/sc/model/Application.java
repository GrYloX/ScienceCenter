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


@Table(name="application")
@Entity
public class Application { 
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
        
	@Column(name="title")
	private String title;
        
	@Column(name="paperabstract")
	private String paperAbstract;
        
	@Column(name="keyterms")
	private String keyTerms;
        
	@Column(name="file")
	private String file;
        
	@ManyToOne(fetch=FetchType.LAZY)
	private User author;
        
	@ManyToOne(fetch=FetchType.LAZY)
	private Magazine magazine;
        
	@ManyToOne(fetch=FetchType.LAZY)
	private ScienceField scienceField;
        
    @OneToMany(fetch=FetchType.LAZY)    
	private List<UserDetails> coauthors = new ArrayList<UserDetails>();
		
        

	public Application() {}

  	public long getId(){
    	return id;
  	}
  
  	public void setId(long id){
       	this.id = id;	
	}
      
  	public String getTitle(){
    	return title;
  	}
  
  	public void setTitle(String title){
       	this.title = title;	
	}
      
  	public String getPaperAbstract(){
    	return paperAbstract;
  	}
  
  	public void setPaperAbstract(String paperAbstract){
       	this.paperAbstract = paperAbstract;	
	}
      
  	public String getKeyTerms(){
    	return keyTerms;
  	}
  
  	public void setKeyTerms(String keyTerms){
       	this.keyTerms = keyTerms;	
	}
      
  	public String getFile(){
    	return file;
  	}
  
  	public void setFile(String file){
       	this.file = file;	
	}
      
  	public User getAuthor(){
    	return author;
  	}
  
  	public void setAuthor(User author){
       	this.author = author;	
	}
      
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
      
   	public void addCoauthors(UserDetails userdetails){
		this.coauthors.add(userdetails);
	}
	
	public void removeCoauthors(UserDetails userdetails){
		coauthors.remove(userdetails);
	}
	
	public List<UserDetails> getCoauthors(){
     	return coauthors;
    }
      
   	public void setCoauthors( List<UserDetails> coauthors){
     	this.coauthors = coauthors;
   	}
	

}