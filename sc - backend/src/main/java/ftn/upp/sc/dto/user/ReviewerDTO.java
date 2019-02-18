package ftn.upp.sc.dto.user;

import java.util.HashSet;
import java.util.Set;

public class ReviewerDTO{
	
	//mogu biti u vise casopisa i pokrivati vise naucnih oblasti, ali ne mogu biti urednici
	
    private Set<String> magazines = new HashSet<String>();
    private Set<Long> fields = new HashSet<Long>();    
    private Integer id;
    private String title;
    private String userId;

    public ReviewerDTO() {
    	
    }

	public ReviewerDTO(Set<String> magazines, Set<Long> fields, String title, String userId) {
		this.magazines = magazines;
		this.fields = fields;
		this.title = title;
		this.userId = userId;
	}
	
	public ReviewerDTO(Set<Long> fields, String title, String userId) {
		this.fields = fields;
		this.title = title;
		this.userId = userId;
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
