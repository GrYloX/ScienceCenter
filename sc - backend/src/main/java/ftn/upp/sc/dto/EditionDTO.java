package ftn.upp.sc.dto;

public class EditionDTO{
	
	 private Long id;
	 private String magazineIssn;
	 private Double price;
	 private String editionName;

	public EditionDTO(){
		 
	 }
	 
	public EditionDTO(String magazineIssn, Double price, String editionName) {
		this.magazineIssn = magazineIssn;
		this.price = price;
		this.editionName = editionName;
	}






	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMagazineIssn() {
		return magazineIssn;
	}

	public void setMagazineIssn(String magazineIssn) {
		this.magazineIssn = magazineIssn;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	 
	public String getEditionName() {
		return editionName;
	}
		
	public void setEditionName(String editionName) {
		this.editionName = editionName;
	}
	 

}
