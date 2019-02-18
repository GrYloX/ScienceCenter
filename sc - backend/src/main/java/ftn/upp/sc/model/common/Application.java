package ftn.upp.sc.model.common;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ftn.upp.sc.model.users.User;
import ftn.upp.sc.model.users.UserDetails;

@Entity
public class Application {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String title;
	
	@Column(length = 2000, nullable = false)
    private String paperAbstract;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "author_id")
    private User author;
	
	@Column(nullable = false)
	private String keyTerms;
	
	@Column
	@OneToMany(fetch = FetchType.LAZY)
	private Set<UserDetails> coauthors = new HashSet<UserDetails>();
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "magazine_id")
    private Magazine magazine;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "science_field_id")
    private ScienceField scienceField;
	
	@Column(nullable = false)
    private String file;
	
	public Application(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPaperAbstract() {
		return paperAbstract;
	}

	public void setPaperAbstract(String paperAbstract) {
		this.paperAbstract = paperAbstract;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getKeyTerms() {
		return keyTerms;
	}

	public void setKeyTerms(String keyTerms) {
		this.keyTerms = keyTerms;
	}

	public Set<UserDetails> getCoauthors() {
		return coauthors;
	}

	public void setCoauthors(Set<UserDetails> coauthors) {
		this.coauthors = coauthors;
	}

	public Magazine getMagazine() {
		return magazine;
	}

	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}

	public ScienceField getScienceField() {
		return scienceField;
	}

	public void setScienceField(ScienceField scienceField) {
		this.scienceField = scienceField;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	
	
}
