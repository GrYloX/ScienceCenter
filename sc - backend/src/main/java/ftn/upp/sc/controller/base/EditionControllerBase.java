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

import ftn.upp.sc.converter.EditionConverter;
import ftn.upp.sc.dto.EditionDTO;
import ftn.upp.sc.model.Edition;
import ftn.upp.sc.service.EditionService;

@RequestMapping(value="/edition")
public class EditionControllerBase {

	@Autowired
	public EditionService editionService;
	
	@Autowired
	public EditionConverter editionConverter;
	
	@RequestMapping(value="getEditions", method=RequestMethod.GET)
	public ResponseEntity<List<EditionDTO>> getEditions(){
		List<Edition> editions = editionService.findAll();
		List<EditionDTO> editionsDTO = new ArrayList<EditionDTO>();
		for(Edition edition : editions){
			editionsDTO.add(editionConverter.entityToDto(edition));
		}
		return new ResponseEntity<>(editionsDTO, HttpStatus.OK);		
	}	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<EditionDTO> addEdition(@RequestBody EditionDTO editionDTO){
			
		Edition newEdition = editionService.saveEdition(editionDTO);
		return new ResponseEntity<>(editionConverter.entityToDto(newEdition), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<EditionDTO> getEdition(@PathVariable long id) {
		Edition edition = editionService.findOne(id);
		if (edition == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(editionConverter.entityToDto(edition), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<EditionDTO> delete(@PathVariable long id) {
		Edition deleted = editionService.deleteEdition(id);
		return new ResponseEntity<>(editionConverter.entityToDto(deleted), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<EditionDTO> edit(@RequestBody EditionDTO editionDTO) {
		Edition edited = editionService.saveEdition(editionDTO);
		return new ResponseEntity<>(editionConverter.entityToDto(edited), HttpStatus.OK);
	}
}
