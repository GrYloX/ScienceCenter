package ftn.upp.sc.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.upp.sc.model.common.Magazine;
import ftn.upp.sc.model.common.ScienceField;
import ftn.upp.sc.model.users.Reviewer;

@Repository
public interface ReviewerRepository extends JpaRepository<Reviewer, Integer> {
	
	List<Reviewer> findByMagazinesContaining(Magazine magazine);
	List<Reviewer> findByMagazinesContainingAndFieldsContaining(Magazine magazine, ScienceField scienceField);
	
}