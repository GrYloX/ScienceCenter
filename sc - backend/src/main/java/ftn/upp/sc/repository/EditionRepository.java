package ftn.upp.sc.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import ftn.upp.sc.model.Edition;
import ftn.upp.sc.repository.base.EditionRepositoryBase;

@Repository
public interface EditionRepository extends EditionRepositoryBase {
	
	List<Edition> findByMagazine_issn(String magazine_issn);

}
