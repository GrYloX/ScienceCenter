package ftn.upp.sc.service.common;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.common.ApplicationConverter;
import ftn.upp.sc.dto.common.ApplicationDTO;
import ftn.upp.sc.model.common.Application;
import ftn.upp.sc.repository.common.ApplicationRepository;

@Service
public class ApplicationService {
	
	@Autowired
	ApplicationRepository applicationRepository;
	
	@Autowired
	ApplicationConverter applicationConverter;
	
	public List<Application> findAll() {
		return applicationRepository.findAll();
	}
	
	public Application findOne(Long id) {
		Optional<Application> application = applicationRepository.findById(id);
		return application.get();
	}
	
	public Application saveApplication(ApplicationDTO dto) {
		Application application = applicationConverter.DtoToEntity(dto);
		return applicationRepository.save(application);
	}

	public Application deleteApplication(Long id) {
		Application application = this.findOne(id);
		if(application == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant");
		}
		applicationRepository.delete(application);
		return application;
		
		
	}

}
