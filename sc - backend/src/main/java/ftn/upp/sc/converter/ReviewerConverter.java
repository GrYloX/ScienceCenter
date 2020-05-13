package ftn.upp.sc.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.upp.sc.dto.MagazineDTO;
import ftn.upp.sc.dto.ReviewerDTO;
import ftn.upp.sc.model.Magazine;
import ftn.upp.sc.model.Reviewer;
import ftn.upp.sc.model.ScienceField;
import ftn.upp.sc.repository.MagazineRepository;
import ftn.upp.sc.repository.ScienceFieldRepository;
import ftn.upp.sc.repository.UserRepository;

@Component
public class ReviewerConverter implements Converter<Reviewer, ReviewerDTO> {

	private ModelMapper mapper;
	
	@Autowired
	MagazineRepository magazineRepository;
	
	@Autowired
	ScienceFieldRepository scienceFieldRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    public ReviewerConverter(ModelMapper modelMapper){
		this.mapper = modelMapper;
	    this.mapper.addMappings(skipModifiedFieldsMap);
    }
	
	PropertyMap<Reviewer, ReviewerDTO> skipModifiedFieldsMap = new PropertyMap<Reviewer, ReviewerDTO>() {
	      protected void configure() {
	    	 skip(destination.getFields());
	         skip(destination.getMagazines());
	     }
	};
	
	@Override
	public ReviewerDTO entityToDto(Reviewer entity) {
		ReviewerDTO dto = new ReviewerDTO();
		mapper.map(entity, dto);
		for(Magazine m : entity.getMagazines()){
			dto.getMagazines().add(m.getIssn());
		}
		for(ScienceField sf : entity.getFields()){
			dto.getFields().add(sf.getId());
		}
		dto.setUserId(entity.getUser().getUsername());
		return dto;
	}

	@Override
	public Reviewer DtoToEntity(ReviewerDTO dto) {
		Reviewer entity = new Reviewer();
		mapper.map(dto, entity);
		for(String m : dto.getMagazines()){
			entity.getMagazines().add(magazineRepository.getOne(m));
		}
		for(Long sf : dto.getFields()){
			entity.getFields().add(scienceFieldRepository.getOne(sf));
		}
		entity.setUser(userRepository.getOne(dto.getUserId()));
		return entity;
	}

}
