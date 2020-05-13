package ftn.upp.sc.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.upp.sc.dto.UserDetailsDTO;
import ftn.upp.sc.model.UserDetails;

@Component
public class UserDetailsConverter implements Converter<UserDetails, UserDetailsDTO> {

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public UserDetailsDTO entityToDto(UserDetails entity) {
		UserDetailsDTO dto = new UserDetailsDTO();
		mapper.map(entity, dto);
		return dto;
	}

	@Override
	public UserDetails DtoToEntity(UserDetailsDTO dto) {
		UserDetails entity = new UserDetails();
		mapper.map(dto, entity);
		return entity;
	}

}
