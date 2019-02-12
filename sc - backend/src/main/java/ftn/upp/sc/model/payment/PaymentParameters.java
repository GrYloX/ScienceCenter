package ftn.upp.sc.model.payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
public class PaymentParameters {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
	
	@ElementCollection(fetch=FetchType.LAZY)
	@CollectionTable(name = "PaymentParameterValues",joinColumns = @JoinColumn(name = "payment_parameters_id", referencedColumnName = "id"))
	@Column(name = "parameter_values")
	private List<String> parameterValues = new ArrayList<String>();
	
	public PaymentParameters(){
		
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

	@Override
	public String toString() {
		return "PaymentParameters [id=" + id + ", parameterValues=" + parameterValues + "]";
	}
	
	
	
	

}
