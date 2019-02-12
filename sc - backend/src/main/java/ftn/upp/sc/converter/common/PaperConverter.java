package ftn.upp.sc.converter.common;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.upp.sc.converter.Converter;
import ftn.upp.sc.dto.common.PaperDTO;
import ftn.upp.sc.model.common.Paper;
import ftn.upp.sc.model.users.UserDetails;
import ftn.upp.sc.repository.common.ApplicationRepository;
import ftn.upp.sc.repository.common.EditionRepository;

@Component
public class PaperConverter implements Converter<Paper, PaperDTO> {

	private ModelMapper mapper;
	
	@Autowired
	EditionRepository editionRepository;
	
	@Autowired
	ApplicationRepository applicationRepository;
	
	@Autowired
    public PaperConverter(ModelMapper modelMapper){
		this.mapper = modelMapper;
	    this.mapper.addMappings(skipModifiedFieldsMap);
    }
	
	PropertyMap<Paper, PaperDTO> skipModifiedFieldsMap = new PropertyMap<Paper, PaperDTO>() {
	      protected void configure() {
	    	  skip(destination.getEditionId());
	    	  skip(destination.getPaperDetailsId());
	    	  skip(destination.getFileName());
	    	  skip(destination.getAuthor());
	    	  skip(destination.getCoauthors());
	    	  skip(destination.getKeyTerms());
	    	  skip(destination.getPaperAbstract());
	    	  skip(destination.getTitle());
	     }
	};
	
	@Override
	public PaperDTO entityToDto(Paper entity) {
		PaperDTO dto = new PaperDTO();
		mapper.map(entity, dto);
		dto.setEditionId(entity.getEdition().getId());
		dto.setPaperDetailsId(entity.getPaperDetails().getId());
		dto.setFileName(entity.getPaperDetails().getFile());
		dto.setAuthor(entity.getPaperDetails().getAuthor().getUserDetails().getFirstName() + ' '+entity.getPaperDetails().getAuthor().getUserDetails().getLastName());
		for(UserDetails coauthor : entity.getPaperDetails().getCoauthors()){
			dto.getCoauthors().add(coauthor.getFirstName()+' '+coauthor.getLastName());
		}
		dto.setKeyTerms(entity.getPaperDetails().getKeyTerms());
		dto.setPaperAbstract(entity.getPaperDetails().getPaperAbstract());
		dto.setTitle(entity.getPaperDetails().getTitle());
		return dto;
	}

	@Override
	public Paper DtoToEntity(PaperDTO dto) {
		Paper entity = new Paper();
		mapper.map(dto, entity);
		entity.setEdition(editionRepository.getOne(dto.getEditionId()));
		entity.setPaperDetails(applicationRepository.getOne(dto.getPaperDetailsId()));
		return entity;
	}


}
