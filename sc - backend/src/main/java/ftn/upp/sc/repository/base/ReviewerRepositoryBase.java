package ftn.upp.sc.repository.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.upp.sc.model.Reviewer;

public interface ReviewerRepositoryBase extends JpaRepository<Reviewer, Integer> {

}		
