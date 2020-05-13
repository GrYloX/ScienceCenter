package ftn.upp.sc.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import ftn.upp.sc.model.Editor;
import ftn.upp.sc.repository.base.EditorRepositoryBase;

@Repository
public interface EditorRepository extends EditorRepositoryBase {
	
	List<Editor> findByMagazine_issnAndScienceField_id(String magazine_issn, long scienceField_id);
	
}