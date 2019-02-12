package ftn.upp.sc.model.payment;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;


@Entity
public class PaymentOption {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String paymentOptionName;

    @ElementCollection(fetch=FetchType.LAZY)
	@CollectionTable(name = "PaymentOptionParameters",joinColumns = @JoinColumn(name = "payment_option_id", referencedColumnName = "id"))
	@Column(name = "parameter")
	private List<String> parameters = new ArrayList<String>();
    
    
    public PaymentOption() {
    	
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPaymentOptionName() {
		return paymentOptionName;
	}

	public void setPaymentOptionName(String paymentOptionName) {
		this.paymentOptionName = paymentOptionName;
	}

	public List<String> getParameters() {
		return parameters;
	}


	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}


	@Override
	public String toString() {
		return "PaymentOption [id=" + id + ", paymentOptionName=" + paymentOptionName + ", parameters=" + parameters
				+ "]";
	}
    
	
    
}
