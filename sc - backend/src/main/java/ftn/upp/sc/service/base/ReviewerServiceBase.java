package ftn.upp.sc.service.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.ReviewerConverter;
import ftn.upp.sc.dto.ReviewerDTO;
import ftn.upp.sc.model.Reviewer;
import ftn.upp.sc.repository.ReviewerRepository;

public class ReviewerServiceBase {
	
	@Autowired
	public ReviewerRepository reviewerRepository;
	
	@Autowired
	public ReviewerConverter reviewerConverter;
	
	public List<Reviewer> findAll() {
		return reviewerRepository.findAll();
	}
	
	public Reviewer findOne(Integer id) {
		Optional<Reviewer> reviewer = reviewerRepository.findById(id);
		return reviewer.get();
	}
	
	public Reviewer saveReviewer(ReviewerDTO dto) {
		Reviewer reviewer = reviewerConverter.DtoToEntity(dto);
		return reviewerRepository.save(reviewer);
	}

	public Reviewer deleteReviewer(Integer id) {
		Reviewer reviewer = this.findOne(id);
		if(reviewer == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant");
		}
		reviewerRepository.delete(reviewer);
		return reviewer;
	}
}
