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

import ftn.upp.sc.converter.ReviewerConverter;
import ftn.upp.sc.dto.ReviewerDTO;
import ftn.upp.sc.model.Reviewer;
import ftn.upp.sc.service.ReviewerService;

@RequestMapping(value="/reviewer")
public class ReviewerControllerBase {

	@Autowired
	public ReviewerService reviewerService;
	
	@Autowired
	public ReviewerConverter reviewerConverter;
	
	@RequestMapping(value="getReviewers", method=RequestMethod.GET)
	public ResponseEntity<List<ReviewerDTO>> getReviewers(){
		List<Reviewer> reviewers = reviewerService.findAll();
		List<ReviewerDTO> reviewersDTO = new ArrayList<ReviewerDTO>();
		for(Reviewer reviewer : reviewers){
			reviewersDTO.add(reviewerConverter.entityToDto(reviewer));
		}
		return new ResponseEntity<>(reviewersDTO, HttpStatus.OK);		
	}	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<ReviewerDTO> addReviewer(@RequestBody ReviewerDTO reviewerDTO){
			
		Reviewer newReviewer = reviewerService.saveReviewer(reviewerDTO);
		return new ResponseEntity<>(reviewerConverter.entityToDto(newReviewer), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ReviewerDTO> getReviewer(@PathVariable Integer id) {
		Reviewer reviewer = reviewerService.findOne(id);
		if (reviewer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(reviewerConverter.entityToDto(reviewer), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ReviewerDTO> delete(@PathVariable Integer id) {
		Reviewer deleted = reviewerService.deleteReviewer(id);
		return new ResponseEntity<>(reviewerConverter.entityToDto(deleted), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<ReviewerDTO> edit(@RequestBody ReviewerDTO reviewerDTO) {
		Reviewer edited = reviewerService.saveReviewer(reviewerDTO);
		return new ResponseEntity<>(reviewerConverter.entityToDto(edited), HttpStatus.OK);
	}
}
