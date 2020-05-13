package ftn.upp.sc.controller;

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

import ftn.upp.sc.controller.base.PaperControllerBase;
import ftn.upp.sc.converter.PaperConverter;
import ftn.upp.sc.dto.PaperDTO;
import ftn.upp.sc.model.Paper;
import ftn.upp.sc.service.PaperService;


@Controller
@CrossOrigin(origins = "http://localhost:4200", maxAge = 13200)
public class PaperController extends PaperControllerBase {
	
	@RequestMapping(value="getEditionPapers/{edition_id}", method=RequestMethod.GET)
	public ResponseEntity<List<PaperDTO>> getEditionPapers(@PathVariable Long edition_id){
		List<Paper> papers = paperService.findByEditionId(edition_id);
		List<PaperDTO> papersDTO = new ArrayList<PaperDTO>();
		for(Paper paper : papers){
			papersDTO.add(paperConverter.entityToDto(paper));
		}
		return new ResponseEntity<>(papersDTO, HttpStatus.OK);		
	}	
}
