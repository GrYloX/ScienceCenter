package ftn.upp.sc.model.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class User {
	
	//Autori i citaoci
	
	@Id
	@Column(nullable = false, length=30)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_details_id", unique = true)
	private UserDetails userDetails;	
	
	@JsonIgnore
	private int failedLoginAttempts = 0;
		
	public User(){
		
	}
	

	public User(String username, String password, UserDetails userDetails) {
		super();
		this.username = username;
		this.password = password;
		this.userDetails = userDetails;
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

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}


	public int getFailedLoginAttempts() {
		return failedLoginAttempts;
	}


	public void setFailedLoginAttempts(int failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}


	public void incrementFailedLoginAttempts() {
		++this.failedLoginAttempts;
	}
	
	
	
	
	

}
