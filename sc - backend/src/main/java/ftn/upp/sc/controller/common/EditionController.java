package ftn.upp.sc.controller.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ftn.upp.sc.converter.common.EditionConverter;
import ftn.upp.sc.dto.common.EditionDTO;
import ftn.upp.sc.model.common.Edition;
import ftn.upp.sc.service.common.EditionService;


@Controller
@RequestMapping(value="/edition")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 13200)
public class EditionController {

	@Autowired
	private EditionService editionService;
	
	@Autowired
	private EditionConverter editionConverter;
	
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
	public ResponseEntity<EditionDTO> getEdition(@PathVariable Long id) {
		Edition edition = editionService.findOne(id);
		if (edition == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(editionConverter.entityToDto(edition), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<EditionDTO> delete(@PathVariable Long id) {
		Edition deleted = editionService.deleteEdition(id);
		return new ResponseEntity<>(editionConverter.entityToDto(deleted), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<EditionDTO> edit(@RequestBody EditionDTO editionDTO) {
		Edition edited = editionService.saveEdition(editionDTO);
		return new ResponseEntity<>(editionConverter.entityToDto(edited), HttpStatus.OK);
	}
	
	@RequestMapping(value="getMagazineEditions/{magazine_issn}", method=RequestMethod.GET)
	public ResponseEntity<List<EditionDTO>> getMagazineEditions(@PathVariable String magazine_issn){
		List<Edition> editions = editionService.findByMagazineIssn(magazine_issn);
		List<EditionDTO> editionsDTO = new ArrayList<EditionDTO>();
		for(Edition edition : editions){
			editionsDTO.add(editionConverter.entityToDto(edition));
		}
		return new ResponseEntity<>(editionsDTO, HttpStatus.OK);		
	}
}
