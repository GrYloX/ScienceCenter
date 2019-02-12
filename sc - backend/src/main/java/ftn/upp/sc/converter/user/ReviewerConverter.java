package ftn.upp.sc.converter.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.upp.sc.converter.Converter;
import ftn.upp.sc.dto.user.ReviewerDTO;
import ftn.upp.sc.model.common.Magazine;
import ftn.upp.sc.model.common.ScienceField;
import ftn.upp.sc.model.users.Reviewer;
import ftn.upp.sc.repository.common.MagazineRepository;
import ftn.upp.sc.repository.common.ScienceFieldRepository;
import ftn.upp.sc.repository.user.UserRepository;

@Component
public class ReviewerConverter implements Converter<Reviewer, ReviewerDTO> {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	MagazineRepository magazineRepository;
	
	@Autowired
	ScienceFieldRepository scienceFieldRepository;
	
	@Autowired
	UserRepository userRepository;
	
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
