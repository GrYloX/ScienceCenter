package ftn.upp.sc.model.users;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ftn.upp.sc.model.common.Magazine;
import ftn.upp.sc.model.common.ScienceField;

@Entity
public class Editor extends Authority {

	//samo jedan casopis i naucna oblast, ali mogu imati ulogu recenzenta
	
	@ManyToOne
    @JoinColumn(name = "magazine_id")
    private Magazine magazine;

    @ManyToOne(optional = true)
    @JoinColumn(name = "science_field_id")
    private ScienceField scienceField;

    public Editor() {
    	
    }

	public Magazine getMagazine() {
		return magazine;
	}

	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}

	public ScienceField getScienceField() {
		return scienceField;
	}

	public void setScienceField(ScienceField scienceField) {
		this.scienceField = scienceField;
	} 
	
}
