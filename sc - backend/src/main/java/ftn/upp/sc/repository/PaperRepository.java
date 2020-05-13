package ftn.upp.sc.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import ftn.upp.sc.model.Paper;
import ftn.upp.sc.repository.base.PaperRepositoryBase;

@Repository
public interface PaperRepository extends PaperRepositoryBase {
	
	List<Paper> findByEdition_id(long edition_id);
	Paper findByPaperDetails_id(long paperDetails_id);
}
