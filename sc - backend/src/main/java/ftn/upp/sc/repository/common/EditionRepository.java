package ftn.upp.sc.repository.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.upp.sc.model.common.Edition;

@Repository
public interface EditionRepository extends JpaRepository<Edition, Long> {
	
	List<Edition> findByMagazine_issn(String magazine_issn);

}
