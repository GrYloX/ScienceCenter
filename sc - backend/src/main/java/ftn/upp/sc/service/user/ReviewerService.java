package ftn.upp.sc.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.user.ReviewerConverter;
import ftn.upp.sc.dto.user.ReviewerDTO;
import ftn.upp.sc.model.common.Magazine;
import ftn.upp.sc.model.common.ScienceField;
import ftn.upp.sc.model.users.Reviewer;
import ftn.upp.sc.repository.user.ReviewerRepository;


@Service
public class ReviewerService {
	
	@Autowired
	ReviewerRepository reviewerRepository;
	
	@Autowired
	ReviewerConverter reviewerConverter;
	
	public List<Reviewer> findAll() {
		return reviewerRepository.findAll();
	}
	
	public List<Reviewer> findByMagazinesContaining(Magazine magazine){
		return reviewerRepository.findByMagazinesContaining(magazine);
	}
	
	public List<Reviewer> findByMagazinesContainingAndFieldsContaining(Magazine magazine, ScienceField sf){
		return reviewerRepository.findByMagazinesContainingAndFieldsContaining(magazine, sf);
	}
	
	public Reviewer findOne(Integer id) {
		Optional<Reviewer> reviewer = reviewerRepository.findById(id);
		return reviewer.get();
	}
	
	public Reviewer saveReviewer(ReviewerDTO dto) {
		Reviewer reviewer = reviewerConverter.DtoToEntity(dto);
		return reviewerRepository.save(reviewer);
	}
	
	public Reviewer save(Reviewer reviewer){
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
