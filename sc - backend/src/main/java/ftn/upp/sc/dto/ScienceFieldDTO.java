package ftn.upp.sc.dto;


public class ScienceFieldDTO {

	private Long id;
	private String name;
	
	public ScienceFieldDTO(){
		
	}	

	public ScienceFieldDTO(String name) {
		this.name = name;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
