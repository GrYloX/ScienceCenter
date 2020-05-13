package ftn.upp.sc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ftn.upp.sc.controller.base.EditionControllerBase;
import ftn.upp.sc.dto.EditionDTO;
import ftn.upp.sc.model.Edition;


@Controller
@CrossOrigin(origins = "http://localhost:4200", maxAge = 13200)
public class EditionController extends EditionControllerBase {
	
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
