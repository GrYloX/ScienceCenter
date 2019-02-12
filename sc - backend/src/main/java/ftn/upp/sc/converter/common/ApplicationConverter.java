package ftn.upp.sc.converter.common;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.upp.sc.converter.Converter;
import ftn.upp.sc.dto.common.ApplicationDTO;
import ftn.upp.sc.model.common.Application;
import ftn.upp.sc.model.users.UserDetails;
import ftn.upp.sc.repository.common.MagazineRepository;
import ftn.upp.sc.repository.common.ScienceFieldRepository;
import ftn.upp.sc.repository.user.UserDetailsRepository;
import ftn.upp.sc.repository.user.UserRepository;

@Component
public class ApplicationConverter implements Converter<Application, ApplicationDTO> {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@Autowired
	MagazineRepository magazineRepository;
	
	@Autowired
	ScienceFieldRepository scienceFieldRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public ApplicationDTO entityToDto(Application entity) {
		ApplicationDTO dto = new ApplicationDTO();
		mapper.map(entity, dto);
		for(UserDetails ca : entity.getCoauthors()){
			dto.getCoauthors().add(ca.getId());
		}
		dto.setAuthor(entity.getAuthor().getUsername());
		dto.setMagazineIssn(entity.getMagazine().getIssn());
		dto.setScienceFieldId(entity.getScienceField().getId());
		return dto;
	}

	@Override
	public Application DtoToEntity(ApplicationDTO dto) {
		Application entity = new Application();
		mapper.map(dto, entity);
		if(dto.getCoauthors()!=null){
			for(Long ca : dto.getCoauthors()){
				entity.getCoauthors().add(userDetailsRepository.getOne(ca));
			}
		}		
		entity.setAuthor(userRepository.getOne(dto.getAuthor()));
		entity.setMagazine(magazineRepository.getOne(dto.getMagazineIssn()));
		entity.setScienceField(scienceFieldRepository.getOne(dto.getScienceFieldId()));
		return entity;
	}


}
