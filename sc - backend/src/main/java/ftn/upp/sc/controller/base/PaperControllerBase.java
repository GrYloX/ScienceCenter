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

import ftn.upp.sc.converter.PaperConverter;
import ftn.upp.sc.dto.PaperDTO;
import ftn.upp.sc.model.Paper;
import ftn.upp.sc.service.PaperService;

@RequestMapping(value="/paper")
public class PaperControllerBase {

	@Autowired
	public PaperService paperService;
	
	@Autowired
	public PaperConverter paperConverter;
	
	@RequestMapping(value="getPapers", method=RequestMethod.GET)
	public ResponseEntity<List<PaperDTO>> getPapers(){
		List<Paper> papers = paperService.findAll();
		List<PaperDTO> papersDTO = new ArrayList<PaperDTO>();
		for(Paper paper : papers){
			papersDTO.add(paperConverter.entityToDto(paper));
		}
		return new ResponseEntity<>(papersDTO, HttpStatus.OK);		
	}	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<PaperDTO> addPaper(@RequestBody PaperDTO paperDTO){
			
		Paper newPaper = paperService.savePaper(paperDTO);
		return new ResponseEntity<>(paperConverter.entityToDto(newPaper), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PaperDTO> getPaper(@PathVariable String id) {
		Paper paper = paperService.findOne(id);
		if (paper == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(paperConverter.entityToDto(paper), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<PaperDTO> delete(@PathVariable String id) {
		Paper deleted = paperService.deletePaper(id);
		return new ResponseEntity<>(paperConverter.entityToDto(deleted), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<PaperDTO> edit(@RequestBody PaperDTO paperDTO) {
		Paper edited = paperService.savePaper(paperDTO);
		return new ResponseEntity<>(paperConverter.entityToDto(edited), HttpStatus.OK);
	}
}
