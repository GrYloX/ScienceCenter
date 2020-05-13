package ftn.upp.sc.dto;

public class EditorDTO {

	//samo jedan casopis i naucna oblast, ali mogu imati ulogu recenzenta
	
    private String magazineIssn;
    private Long scienceField;
    private Integer id;
    private String title;
    private String userId; //username

    public EditorDTO() {
    	
    }
    
	public EditorDTO(String magazineIssn, Long scienceField, String title, String userId) {		
		this.magazineIssn = magazineIssn;
		this.scienceField = scienceField;
		this.title = title;
		this.userId = userId;
	}


	public String getMagazineIssn() {
		return magazineIssn;
	}

	public void setMagazineIssn(String magazineIssn) {
		this.magazineIssn = magazineIssn;
	}

	public Long getScienceField() {
		return scienceField;
	}

	public void setScienceField(Long scienceField) {
		this.scienceField = scienceField;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
 
	
}
