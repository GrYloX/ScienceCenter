package ftn.upp.sc.dto;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {
	
	//Autori i citaoci
	
	private String username;
	private String password;
	private Long userDetailsId;	
	private Set<Long> editions = new HashSet<Long>();
	private Set<String> papers = new HashSet<String>();
		
	public UserDTO(){
		
	}

	public UserDTO(String username, String password, Long userDetailsId) {
		super();
		this.username = username;
		this.password = password;
		this.userDetailsId = userDetailsId;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getUserDetailsId() {
		return userDetailsId;
	}

	public void setUserDetailsId(Long userDetailsId) {
		this.userDetailsId = userDetailsId;
	}

	@Override
	public String toString() {
		return "UserDTO [username=" + username + ", password=" + password + ", userDetailsId=" + userDetailsId + "]";
	}

	public Set<Long> getEditions() {
		return editions;
	}

	public void setEditions(Set<Long> editions) {
		this.editions = editions;
	}

	public Set<String> getPapers() {
		return papers;
	}

	public void setPapers(Set<String> papers) {
		this.papers = papers;
	}
	
	

	
	
	

}
