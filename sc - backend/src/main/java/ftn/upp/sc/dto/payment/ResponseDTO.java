package ftn.upp.sc.dto.payment;

public class ResponseDTO {

	private String url;

	public ResponseDTO(String url) {
		this.url = url;
	}
	
	public ResponseDTO(){
		
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
