package ftn.upp.sc.service.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.ApplicationConverter;
import ftn.upp.sc.dto.ApplicationDTO;
import ftn.upp.sc.model.Application;
import ftn.upp.sc.repository.ApplicationRepository;

public class ApplicationServiceBase {
	
	@Autowired
	public ApplicationRepository applicationRepository;
	
	@Autowired
	public ApplicationConverter applicationConverter;
	
	public List<Application> findAll() {
		return applicationRepository.findAll();
	}
	
	public Application findOne(long id) {
		Optional<Application> application = applicationRepository.findById(id);
		return application.get();
	}
	
	public Application saveApplication(ApplicationDTO dto) {
		Application application = applicationConverter.DtoToEntity(dto);
		return applicationRepository.save(application);
	}

	public Application deleteApplication(long id) {
		Application application = this.findOne(id);
		if(application == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant");
		}
		applicationRepository.delete(application);
		return application;
	}
}
