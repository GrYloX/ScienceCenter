package ftn.upp.sc.model.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ftn.upp.sc.model.enums.MagazinePaymentType;
import ftn.upp.sc.model.payment.PaymentOption;
import ftn.upp.sc.model.payment.PaymentParameters;
import ftn.upp.sc.model.users.Editor;

@Entity
public class Magazine {
	
	@Id
	@Column(nullable = false, length = 13)
	private String issn;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "magazine_id"),
            inverseJoinColumns = @JoinColumn(name = "science_field_id"))
	private Set<ScienceField> scienceFields = new HashSet<>();	
	
	@Column
    private Double membershipPrice;
	
	@Column
	private MagazinePaymentType paymentType;
	
	@OneToOne
    @JoinColumn(name = "main_editor_id", unique = true, nullable = true)
	private Editor mainEditor;
	
	
	@ManyToMany
	@JsonIgnore
    private Map<PaymentOption,PaymentParameters> paymentOptionParameters = new HashMap<PaymentOption,PaymentParameters>();

	
	public Magazine(){
		this.paymentType=MagazinePaymentType.paid_access;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public Set<ScienceField> getScienceFields() {
		return scienceFields;
	}

	public void setScienceFields(Set<ScienceField> scienceFields) {
		this.scienceFields = scienceFields;
	}

	public MagazinePaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(MagazinePaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Editor getMainEditor() {
		return mainEditor;
	}

	public void setMainEditor(Editor mainEditor) {
		this.mainEditor = mainEditor;
	}

	public Map<PaymentOption, PaymentParameters> getPaymentOptionParameters() {
		return paymentOptionParameters;
	}

	public void setPaymentOptionParameters(Map<PaymentOption, PaymentParameters> paymentOptionParameters) {
		this.paymentOptionParameters = paymentOptionParameters;
	}

	public Double getMembershipPrice() {
		return membershipPrice;
	}

	public void setMembershipPrice(Double membershipPrice) {
		this.membershipPrice = membershipPrice;
	}
	
	
	
	

}
