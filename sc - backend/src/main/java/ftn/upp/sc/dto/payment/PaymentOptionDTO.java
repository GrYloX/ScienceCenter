package ftn.upp.sc.dto.payment;

import java.util.List;
import java.util.Set;


public class PaymentOptionDTO {
	
    private Long id;
    private String paymentOptionName;
	private List<String> parameters;
    
    
    public PaymentOptionDTO() {
    	
    }
    


	public PaymentOptionDTO(String paymentOptionName, List<String> parameters) {
		this.paymentOptionName = paymentOptionName;
		this.parameters = parameters;
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

    
    
}
