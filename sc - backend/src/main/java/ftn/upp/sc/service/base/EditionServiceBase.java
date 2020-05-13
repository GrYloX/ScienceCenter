package ftn.upp.sc.service.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.EditionConverter;
import ftn.upp.sc.dto.EditionDTO;
import ftn.upp.sc.model.Edition;
import ftn.upp.sc.repository.EditionRepository;

public class EditionServiceBase {
	
	@Autowired
	public EditionRepository editionRepository;
	
	@Autowired
	public EditionConverter editionConverter;
	
	public List<Edition> findAll() {
		return editionRepository.findAll();
	}
	
	public Edition findOne(long id) {
		Optional<Edition> edition = editionRepository.findById(id);
		return edition.get();
	}
	
	public Edition saveEdition(EditionDTO dto) {
		Edition edition = editionConverter.DtoToEntity(dto);
		return editionRepository.save(edition);
	}

	public Edition deleteEdition(long id) {
		Edition edition = this.findOne(id);
		if(edition == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant");
		}
		editionRepository.delete(edition);
		return edition;
	}
}
