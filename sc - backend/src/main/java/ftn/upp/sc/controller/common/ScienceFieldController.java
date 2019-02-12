package ftn.upp.sc.controller.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ftn.upp.sc.converter.common.ScienceFieldConverter;
import ftn.upp.sc.dto.common.ScienceFieldDTO;
import ftn.upp.sc.model.common.ScienceField;
import ftn.upp.sc.service.common.ScienceFieldService;


@Controller
@RequestMapping(value="/scienceField")
public class ScienceFieldController {

	@Autowired
	private ScienceFieldService scienceFieldService;
	
	@Autowired
	private ScienceFieldConverter scienceFieldConverter;
	
	@RequestMapping(value="getScienceFields", method=RequestMethod.GET)
	public ResponseEntity<List<ScienceFieldDTO>> getScienceFields(){
		List<ScienceField> scienceFields = scienceFieldService.findAll();
		List<ScienceFieldDTO> scienceFieldsDTO = new ArrayList<ScienceFieldDTO>();
		for(ScienceField scienceField : scienceFields){
			scienceFieldsDTO.add(scienceFieldConverter.entityToDto(scienceField));
		}
		return new ResponseEntity<>(scienceFieldsDTO, HttpStatus.OK);		
	}	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<ScienceFieldDTO> addScienceField(@RequestBody ScienceFieldDTO scienceFieldDTO){
			
		ScienceField newScienceField = scienceFieldService.saveScienceField(scienceFieldDTO);
		return new ResponseEntity<>(scienceFieldConverter.entityToDto(newScienceField), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ScienceFieldDTO> getScienceField(@PathVariable Long id) {
		ScienceField scienceField = scienceFieldService.findOne(id);
		if (scienceField == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(scienceFieldConverter.entityToDto(scienceField), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ScienceFieldDTO> delete(@PathVariable Long id) {
		ScienceField deleted = scienceFieldService.deleteScienceField(id);
		return new ResponseEntity<>(scienceFieldConverter.entityToDto(deleted), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<ScienceFieldDTO> edit(@RequestBody ScienceFieldDTO scienceFieldDTO) {
		ScienceField edited = scienceFieldService.saveScienceField(scienceFieldDTO);
		return new ResponseEntity<>(scienceFieldConverter.entityToDto(edited), HttpStatus.OK);
	}
}
