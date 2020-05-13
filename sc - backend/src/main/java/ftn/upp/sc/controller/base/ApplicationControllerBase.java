package ftn.upp.sc.controller.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ftn.upp.sc.converter.ApplicationConverter;
import ftn.upp.sc.dto.ApplicationDTO;
import ftn.upp.sc.model.Application;
import ftn.upp.sc.service.ApplicationService;

@RequestMapping(value="/application")
public class ApplicationControllerBase {

	@Autowired
	public ApplicationService applicationService;
	
	@Autowired
	public ApplicationConverter applicationConverter;
	
	@RequestMapping(value="getApplications", method=RequestMethod.GET)
	public ResponseEntity<List<ApplicationDTO>> getApplications(){
		List<Application> applications = applicationService.findAll();
		List<ApplicationDTO> applicationsDTO = new ArrayList<ApplicationDTO>();
		for(Application application : applications){
			applicationsDTO.add(applicationConverter.entityToDto(application));
		}
		return new ResponseEntity<>(applicationsDTO, HttpStatus.OK);		
	}	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<ApplicationDTO> addApplication(@RequestBody ApplicationDTO applicationDTO){
			
		Application newApplication = applicationService.saveApplication(applicationDTO);
		return new ResponseEntity<>(applicationConverter.entityToDto(newApplication), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ApplicationDTO> getApplication(@PathVariable long id) {
		Application application = applicationService.findOne(id);
		if (application == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(applicationConverter.entityToDto(application), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ApplicationDTO> delete(@PathVariable long id) {
		Application deleted = applicationService.deleteApplication(id);
		return new ResponseEntity<>(applicationConverter.entityToDto(deleted), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<ApplicationDTO> edit(@RequestBody ApplicationDTO applicationDTO) {
		Application edited = applicationService.saveApplication(applicationDTO);
		return new ResponseEntity<>(applicationConverter.entityToDto(edited), HttpStatus.OK);
	}
}
