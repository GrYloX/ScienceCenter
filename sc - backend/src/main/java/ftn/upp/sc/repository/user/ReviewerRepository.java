package ftn.upp.sc.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.upp.sc.model.users.Reviewer;

@Repository
public interface ReviewerRepository extends JpaRepository<Reviewer, Integer> {
	
}