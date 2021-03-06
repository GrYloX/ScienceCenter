package ftn.upp.sc.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.upp.sc.dto.MagazineDTO;
import ftn.upp.sc.dto.UserDTO;
import ftn.upp.sc.model.Magazine;
import ftn.upp.sc.model.User;
import ftn.upp.sc.repository.UserDetailsRepository;

@Component
public class UserConverter implements Converter<User, UserDTO> {

	private ModelMapper mapper;
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@Autowired
    public UserConverter(ModelMapper modelMapper){
		this.mapper = modelMapper;
	    this.mapper.addMappings(skipModifiedFieldsMap);
    }
	
	PropertyMap<User, UserDTO> skipModifiedFieldsMap = new PropertyMap<User, UserDTO>() {
	      protected void configure() {
	    	 skip(destination.getEditions());
	    	 skip(destination.getUserDetailsId());
	    	 skip(destination.getPapers());
	     }
	};
	
	@Override
	public UserDTO entityToDto(User entity) {
		UserDTO dto = new UserDTO();
		mapper.map(entity, dto);
		dto.setUserDetailsId(entity.getUserDetails().getId());
		return dto;
	}

	@Override
	public User DtoToEntity(UserDTO dto) {
		User entity = new User();
		mapper.map(dto, entity);
		entity.setUserDetails(userDetailsRepository.getOne(dto.getUserDetailsId()));
		return entity;
	}

}
