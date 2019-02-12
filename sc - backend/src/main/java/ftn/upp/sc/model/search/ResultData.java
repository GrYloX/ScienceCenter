package ftn.upp.sc.model.search;

public final class ResultData {
	
	private String title;
	private String doi;
	private String authorName;
	private String highlight;
	private Double price;
	private String filename;
	private String magazineName;
	private Boolean free;
	
	public ResultData() {
		super();
	}
	
	

	public ResultData(String title, String doi, String authorName, String highlight, Double price, String filename, String magazineName, Boolean free) {
		super();
		this.title = title;
		this.doi = doi;
		this.authorName = authorName;
		this.highlight = highlight;
		this.price = price;
		this.filename = filename;
		this.magazineName = magazineName;
		this.free = free;
	}



	
	public Boolean getFree() {
		return free;
	}



	public void setFree(Boolean free) {
		this.free = free;
	}



	public String getMagazineName() {
		return magazineName;
	}



	public void setMagazineName(String magazineName) {
		this.magazineName = magazineName;
	}



	public String getDoi() {
		return doi;
	}



	public void setDoi(String doi) {
		this.doi = doi;
	}



	public String getAuthorName() {
		return authorName;
	}



	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}



	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public String getFilename() {
		return filename;
	}



	public void setFilename(String filename) {
		this.filename = filename;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

}
