package ftn.upp.sc.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.upp.sc.model.users.Editor;

@Repository
public interface EditorRepository extends JpaRepository<Editor, Integer> {
	
	List<Editor> findByMagazine_issnAndScienceField_id(String magazine_issn, long scienceField_id);
	
}