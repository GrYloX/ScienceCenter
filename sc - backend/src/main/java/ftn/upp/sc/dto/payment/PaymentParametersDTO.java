package ftn.upp.sc.dto.payment;

import java.util.List;

public class PaymentParametersDTO {
	
    private Long id;
    private List<String> parameterValues;
    
    public PaymentParametersDTO(){
    	
    }
    
    

	public PaymentParametersDTO(List<String> parameterValues) {
		this.parameterValues = parameterValues;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<String> getParameterValues() {
		return parameterValues;
	}

	public void setParameterValues(List<String> parameterValues) {
		this.parameterValues = parameterValues;
	}
	
	
    
    

}
