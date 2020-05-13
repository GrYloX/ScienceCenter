package ftn.upp.sc.service.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.PaperConverter;
import ftn.upp.sc.dto.PaperDTO;
import ftn.upp.sc.model.Paper;
import ftn.upp.sc.repository.PaperRepository;

public class PaperServiceBase {
	
	@Autowired
	public PaperRepository paperRepository;
	
	@Autowired
	public PaperConverter paperConverter;
	
	public List<Paper> findAll() {
		return paperRepository.findAll();
	}
	
	public Paper findOne(String id) {
		Optional<Paper> paper = paperRepository.findById(id);
		return paper.get();
	}
	
	public Paper savePaper(PaperDTO dto) {
		Paper paper = paperConverter.DtoToEntity(dto);
		return paperRepository.save(paper);
	}

	public Paper deletePaper(String id) {
		Paper paper = this.findOne(id);
		if(paper == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant");
		}
		paperRepository.delete(paper);
		return paper;
	}
}
