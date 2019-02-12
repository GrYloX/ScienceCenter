package ftn.upp.sc.model.users;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class Authority {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	protected Integer id;
	
	@Column
	protected String title;
	
	@OneToOne(optional = false)
    @JoinColumn(name = "user_id", unique = true)
	protected User user;
	
	public Authority() {
    }

    public Authority(String title, Integer id, User user) {
        this.title = title;
        this.id = id;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
}
	
	
}
