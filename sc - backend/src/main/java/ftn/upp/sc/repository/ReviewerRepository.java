package ftn.upp.sc.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import ftn.upp.sc.model.Magazine;
import ftn.upp.sc.model.Reviewer;
import ftn.upp.sc.model.ScienceField;
import ftn.upp.sc.repository.base.ReviewerRepositoryBase;

@Repository
public interface ReviewerRepository extends ReviewerRepositoryBase {
	
	List<Reviewer> findByMagazinesContaining(Magazine magazine);
	List<Reviewer> findByMagazinesContainingAndFieldsContaining(Magazine magazine, ScienceField scienceField);
	
}