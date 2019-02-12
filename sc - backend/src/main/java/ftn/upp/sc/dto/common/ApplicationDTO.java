package ftn.upp.sc.dto.common;
import java.util.Set;

public class ApplicationDTO {

    private Long id;
    private String title;
    private String paperAbstract;
    private String author;
	private String keyTerms;
	private Set<Long> coauthors;
    private String magazineIssn;
    private Long scienceFieldId;
	private String file;
    
    public ApplicationDTO(){
    	
    }
    


	public ApplicationDTO(String title, String paperAbstract, String author, String keyTerms, String magazineIssn,
			Long scienceFieldId, String file) {		
		this.title = title;
		this.paperAbstract = paperAbstract;
		this.author = author;
		this.keyTerms = keyTerms;
		this.magazineIssn = magazineIssn;
		this.scienceFieldId = scienceFieldId;
		this.file = file;
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

	public Set<Long> getCoauthors() {
		return coauthors;
	}

	public void setCoauthors(Set<Long> coauthors) {
		this.coauthors = coauthors;
	}

	public String getMagazineIssn() {
		return magazineIssn;
	}

	public void setMagazineIssn(String magazineIssn) {
		this.magazineIssn = magazineIssn;
	}

	public Long getScienceFieldId() {
		return scienceFieldId;
	}

	public void setScienceFieldId(Long scienceFieldId) {
		this.scienceFieldId = scienceFieldId;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
    
}
