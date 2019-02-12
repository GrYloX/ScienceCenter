package ftn.upp.sc.service.common;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.common.PaperConverter;
import ftn.upp.sc.dto.common.PaperDTO;
import ftn.upp.sc.model.common.Application;
import ftn.upp.sc.model.common.Paper;
import ftn.upp.sc.repository.common.ApplicationRepository;
import ftn.upp.sc.repository.common.PaperRepository;
import ftn.upp.sc.service.Indexer;
import ftn.upp.sc.service.storage.StorageService;

@Service
public class PaperService {
	
	@Autowired
	PaperRepository paperRepository;
	
	@Autowired
	PaperConverter paperConverter;
	
	@Autowired
	ApplicationRepository applicationRepository;
	
	@Autowired
	Indexer indexerService;
	
	private final /*FileSystem*/ StorageService storageService;
	
	@Autowired
    public PaperService(/*FileSystem*/StorageService storageService) {
        this.storageService = storageService;
    }
	
	public List<Paper> findAll() {
		return paperRepository.findAll();
	}
	
	public Paper findOne(String id) {
		Optional<Paper> paper = paperRepository.findById(id);
		return paper.get();
	}
	
	public Paper savePaper(PaperDTO dto) {
		Paper paper = paperConverter.DtoToEntity(dto);
		Application a = applicationRepository.findById(dto.getPaperDetailsId()).get();
		Path path = storageService.load(a.getFile());
		indexerService.index(new File(path.toUri()));
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
	
	public List<Paper> findByEditionId(Long edition_id){
		return paperRepository.findByEdition_id(edition_id);
	}

}
