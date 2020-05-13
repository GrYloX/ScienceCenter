package ftn.upp.sc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.ApplicationConverter;
import ftn.upp.sc.dto.ApplicationDTO;
import ftn.upp.sc.model.Application;
import ftn.upp.sc.repository.ApplicationRepository;
import ftn.upp.sc.service.base.ApplicationServiceBase;

@Service
public class ApplicationService extends ApplicationServiceBase{
	
	@Autowired
	ApplicationRepository applicationRepository;
	
	@Autowired
	ApplicationConverter applicationConverter;
	
	public ApplicationDTO findOneDto(Long id) {
		Optional<Application> application = applicationRepository.findById(id);
		return applicationConverter.entityToDto(application.get());
	}
	
	public Application save(Application application) {
		return applicationRepository.save(application);
	}

}
