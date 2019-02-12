package ftn.upp.sc.repository.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.upp.sc.model.common.Paper;

@Repository
public interface PaperRepository extends JpaRepository<Paper, String> {
	
	List<Paper> findByEdition_id(long edition_id);
	Paper findByPaperDetails_id(long paperDetails_id);
}
