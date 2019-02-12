package ftn.upp.sc.service.common;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.common.EditionConverter;
import ftn.upp.sc.dto.common.EditionDTO;
import ftn.upp.sc.model.common.Edition;
import ftn.upp.sc.repository.common.EditionRepository;

@Service
public class EditionService {
	
	@Autowired
	EditionRepository editionRepository;
	
	@Autowired
	EditionConverter editionConverter;
	
	public List<Edition> findAll() {
		return editionRepository.findAll();
	}
	
	public Edition findOne(Long id) {
		Optional<Edition> edition = editionRepository.findById(id);
		return edition.get();
	}
	
	public Edition saveEdition(EditionDTO dto) {
		Edition edition = editionConverter.DtoToEntity(dto);
		return editionRepository.save(edition);
	}

	public Edition deleteEdition(Long id) {
		Edition edition = this.findOne(id);
		if(edition == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant");
		}
		editionRepository.delete(edition);
		return edition;	
	}
	
	public List<Edition> findByMagazineIssn(String magazine_issn){
		return editionRepository.findByMagazine_issn(magazine_issn);
	}

}
