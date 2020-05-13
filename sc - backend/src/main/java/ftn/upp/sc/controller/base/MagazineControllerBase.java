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

import ftn.upp.sc.converter.MagazineConverter;
import ftn.upp.sc.dto.MagazineDTO;
import ftn.upp.sc.model.Magazine;
import ftn.upp.sc.service.MagazineService;

@RequestMapping(value="/magazine")
public class MagazineControllerBase {

	@Autowired
	public MagazineService magazineService;
	
	@Autowired
	public MagazineConverter magazineConverter;
	
	@RequestMapping(value="getMagazines", method=RequestMethod.GET)
	public ResponseEntity<List<MagazineDTO>> getMagazines(){
		List<Magazine> magazines = magazineService.findAll();
		List<MagazineDTO> magazinesDTO = new ArrayList<MagazineDTO>();
		for(Magazine magazine : magazines){
			magazinesDTO.add(magazineConverter.entityToDto(magazine));
		}
		return new ResponseEntity<>(magazinesDTO, HttpStatus.OK);		
	}	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<MagazineDTO> addMagazine(@RequestBody MagazineDTO magazineDTO){
			
		Magazine newMagazine = magazineService.saveMagazine(magazineDTO);
		return new ResponseEntity<>(magazineConverter.entityToDto(newMagazine), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<MagazineDTO> getMagazine(@PathVariable String id) {
		Magazine magazine = magazineService.findOne(id);
		if (magazine == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(magazineConverter.entityToDto(magazine), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<MagazineDTO> delete(@PathVariable String id) {
		Magazine deleted = magazineService.deleteMagazine(id);
		return new ResponseEntity<>(magazineConverter.entityToDto(deleted), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<MagazineDTO> edit(@RequestBody MagazineDTO magazineDTO) {
		Magazine edited = magazineService.saveMagazine(magazineDTO);
		return new ResponseEntity<>(magazineConverter.entityToDto(edited), HttpStatus.OK);
	}
}
