package ftn.upp.sc.service;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.PaperConverter;
import ftn.upp.sc.dto.PaperDTO;
import ftn.upp.sc.model.Application;
import ftn.upp.sc.model.Paper;
import ftn.upp.sc.repository.ApplicationRepository;
import ftn.upp.sc.repository.PaperRepository;
import ftn.upp.sc.service.base.PaperServiceBase;
import ftn.upp.sc.service.storage.StorageService;

@Service
public class PaperService extends PaperServiceBase {
		
	@Autowired
	ApplicationRepository applicationRepository;
	
	private final /*FileSystem*/ StorageService storageService;
	
	@Autowired
    public PaperService(/*FileSystem*/StorageService storageService) {
        this.storageService = storageService;
    }
	
	@Override
	public Paper savePaper(PaperDTO dto) {
		Paper paper = paperConverter.DtoToEntity(dto);
		Application a = applicationRepository.findById(dto.getPaperDetailsId()).get();
		Path path = storageService.load(a.getFile());
		return paperRepository.save(paper);
	}
	
	public List<Paper> findByEditionId(Long edition_id){
		return paperRepository.findByEdition_id(edition_id);
	}

}
