package ftn.upp.sc.dto;

import java.util.HashSet;
import java.util.Set;

public class PaperDTO {
	
	private String doi;
	private Long paperDetailsId;
    private Double price;
    private Long editionId;
    
    //iz paperDetails
    private String fileName;    
    private String title;
    private String paperAbstract;
    private String author;
	private String keyTerms;
	private Set<String> coauthors = new HashSet<String>(); // napravicu FirstName + ' ' + LastName
    
	public PaperDTO(){
		
	}
	
	
	public PaperDTO(String doi, Long paperDetailsId, Double price, Long editionId) {
		this.doi = doi;
		this.paperDetailsId = paperDetailsId;
		this.price = price;
		this.editionId = editionId;
	}


	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public Long getPaperDetailsId() {
		return paperDetailsId;
	}

	public void setPaperDetailsId(Long paperDetailsId) {
		this.paperDetailsId = paperDetailsId;
	}

	public Long getEditionId() {
		return editionId;
	}

	public void setEditionId(Long editionId) {
		this.editionId = editionId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
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


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getKeyTerms() {
		return keyTerms;
	}


	public void setKeyTerms(String keyTerms) {
		this.keyTerms = keyTerms;
	}


	public Set<String> getCoauthors() {
		return coauthors;
	}


	public void setCoauthors(Set<String> coauthors) {
		this.coauthors = coauthors;
	}
	
	

	
	
	
	
}
