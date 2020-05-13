package ftn.upp.sc.dto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ftn.upp.sc.model.MagazinePaymentType;

public class MagazineDTO {
	
	private String issn;
	private String name;
	private Set<Long> scienceFields = new HashSet<Long>();	//id's
	private Set<String> scienceFieldNames = new HashSet<String>();
    private Double membershipPrice;
	private MagazinePaymentType paymentType;
	private Integer mainEditorId;
	private String mainEditorFirstName;
	private String mainEditorLastName;
    private Map<Long, Long> paymentOptionParameters = new HashMap<Long, Long>(); //id's  optionId-parametersId
    
	public MagazineDTO(){
		
	}
	

	public MagazineDTO(String issn, String name, Set<Long> scienceFields, Double membershipPrice,
			MagazinePaymentType paymentType, Map<Long, Long> paymentOptionParameters) {
		super();
		this.issn = issn;
		this.name = name;
		this.scienceFields = scienceFields;
		this.membershipPrice = membershipPrice;
		this.paymentType = paymentType;
		this.paymentOptionParameters = paymentOptionParameters;
	}
	
	public MagazineDTO(String issn, String name, Set<Long> scienceFields, Double membershipPrice,
			MagazinePaymentType paymentType, Integer mainEditorId, Map<Long, Long> paymentOptionParameters) {
		super();
		this.issn = issn;
		this.name = name;
		this.scienceFields = scienceFields;
		this.membershipPrice = membershipPrice;
		this.paymentType = paymentType;
		this.mainEditorId = mainEditorId;
		this.paymentOptionParameters = paymentOptionParameters;
	}


	public String getMainEditorFirstName() {
		return mainEditorFirstName;
	}


	public void setMainEditorFirstName(String mainEditorFirstName) {
		this.mainEditorFirstName = mainEditorFirstName;
	}


	public String getMainEditorLastName() {
		return mainEditorLastName;
	}


	public void setMainEditorLastName(String mainEditorLastName) {
		this.mainEditorLastName = mainEditorLastName;
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

	public MagazinePaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(MagazinePaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Set<Long> getScienceFields() {
		return scienceFields;
	}

	public void setScienceFields(Set<Long> scienceFields) {
		this.scienceFields = scienceFields;
	}

	public Map<Long, Long> getPaymentOptionParameters() {
		return paymentOptionParameters;
	}

	public void setPaymentOptionParameters(Map<Long, Long> paymentOptionParameters) {
		this.paymentOptionParameters = paymentOptionParameters;
	}

	public Double getMembershipPrice() {
		return membershipPrice;
	}

	public void setMembershipPrice(Double membershipPrice) {
		this.membershipPrice = membershipPrice;
	}

	public Integer getMainEditorId() {
		return mainEditorId;
	}

	public void setMainEditorId(Integer mainEditorId) {
		this.mainEditorId = mainEditorId;
	}


	public Set<String> getScienceFieldNames() {
		return scienceFieldNames;
	}


	public void setScienceFieldNames(Set<String> scienceFieldNames) {
		this.scienceFieldNames = scienceFieldNames;
	}

	
	
	
	
	

}
