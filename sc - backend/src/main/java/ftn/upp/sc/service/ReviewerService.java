package ftn.upp.sc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.ReviewerConverter;
import ftn.upp.sc.dto.ReviewerDTO;
import ftn.upp.sc.model.Magazine;
import ftn.upp.sc.model.Reviewer;
import ftn.upp.sc.model.ScienceField;
import ftn.upp.sc.repository.ReviewerRepository;
import ftn.upp.sc.service.base.ReviewerServiceBase;


@Service
public class ReviewerService extends ReviewerServiceBase {
	
	public List<Reviewer> findByMagazinesContaining(Magazine magazine){
		return reviewerRepository.findByMagazinesContaining(magazine);
	}
	
	public List<Reviewer> findByMagazinesContainingAndFieldsContaining(Magazine magazine, ScienceField sf){
		return reviewerRepository.findByMagazinesContainingAndFieldsContaining(magazine, sf);
	}
	
	public Reviewer save(Reviewer reviewer){
		return reviewerRepository.save(reviewer);
	}

}
