package ftn.upp.sc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.EditionConverter;
import ftn.upp.sc.dto.EditionDTO;
import ftn.upp.sc.model.Edition;
import ftn.upp.sc.repository.EditionRepository;
import ftn.upp.sc.service.base.EditionServiceBase;

@Service
public class EditionService extends EditionServiceBase {
	
	public List<Edition> findByMagazineIssn(String magazine_issn){
		return editionRepository.findByMagazine_issn(magazine_issn);
	}

}
