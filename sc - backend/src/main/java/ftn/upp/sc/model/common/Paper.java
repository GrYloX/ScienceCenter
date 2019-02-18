package ftn.upp.sc.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Paper {

	@Id
	@Column(nullable = false)
	private String doi;
	
	@OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "paper_details_id", unique = true)
	private Application paperDetails;
	
	@Column
    private Double price;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "edition_id")
    private Edition edition;

	public Paper(){
		
	}
	
	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public Application getPaperDetails() {
		return paperDetails;
	}

	public void setPaperDetails(Application paperDetails) {
		this.paperDetails = paperDetails;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Edition getEdition() {
		return edition;
	}

	public void setEdition(Edition edition) {
		this.edition = edition;
	}
	
	
	
	
}
