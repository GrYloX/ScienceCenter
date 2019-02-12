package ftn.upp.sc.model.users;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import ftn.upp.sc.model.common.Magazine;
import ftn.upp.sc.model.common.ScienceField;

@Entity
public class Reviewer extends Authority {
	
	//mogu biti u vise casopisa i pokrivati vise naucnih oblasti, ali ne mogu biti urednici
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "reviewer_id"),
            inverseJoinColumns = @JoinColumn(name = "magazine_id"))
    private Set<Magazine> magazines = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "reviewer_id"),
            inverseJoinColumns = @JoinColumn(name = "field_id"))
    private Set<ScienceField> fields = new HashSet<>();

    public Reviewer() {
    	
    }

	public Reviewer(String title, Integer id, User user, Set<Magazine> magazines, Set<ScienceField> fields) {
		super(title, id, user);
		this.magazines = magazines;
		this.fields = fields;
	}
	
	public Set<Magazine> getMagazines() {
        return magazines;
    }

    public void setMagazines(Set<Magazine> magazines) {
        this.magazines = magazines;
    }

    public Set<ScienceField> getFields() {
        return fields;
    }

    public void setFields(Set<ScienceField> fields) {
        this.fields = fields;
    }


}
