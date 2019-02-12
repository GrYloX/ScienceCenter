package ftn.upp.sc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUri {
	
	@Value("${spring.data.bank.a}")
	private String bankAUri;
	
	@Value("${spring.data.bank.b}")
	private String bankBUri;
	
	@Value("${spring.data.pcc}")
	private String pccUri;
	
	@Value("${spring.data.kp}")
	private String kpUri;

	public String getPccUri() {
		return pccUri;
	}

	public void setPccUri(String pccUri) {
		this.pccUri = pccUri;
	}

	public String getKpUri() {
		return kpUri;
	}

	public void setKpUri(String kpUri) {
		this.kpUri = kpUri;
	}
	
	public String getBankAUri() {
		return bankAUri;
	}

	public void setBankAUri(String bankAUri) {
		this.bankAUri = bankAUri;
	}

	public String getBankBUri() {
		return bankBUri;
	}

	public void setBankBUri(String bankBUri) {
		this.bankBUri = bankBUri;
	}
	
	

}
