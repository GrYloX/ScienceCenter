package ftn.upp.sc.dto.user;

import java.util.Set;

public class ReviewerDTO{
	
	//mogu biti u vise casopisa i pokrivati vise naucnih oblasti, ali ne mogu biti urednici
	
    private Set<String> magazines;
    private Set<Long> fields;    
    private Integer id;
    private String title;
    private String userId;

    public ReviewerDTO() {
    	
    }

	public Set<String> getMagazines() {
		return magazines;
	}

	public void setMagazines(Set<String> magazines) {
		this.magazines = magazines;
	}

	public Set<Long> getFields() {
		return fields;
	}

	public void setFields(Set<Long> fields) {
		this.fields = fields;
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
