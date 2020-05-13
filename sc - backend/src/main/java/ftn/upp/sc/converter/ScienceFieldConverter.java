package ftn.upp.sc.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.upp.sc.dto.ScienceFieldDTO;
import ftn.upp.sc.model.ScienceField;

@Component
public class ScienceFieldConverter implements Converter<ScienceField, ScienceFieldDTO> {

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ScienceFieldDTO entityToDto(ScienceField entity) {
		ScienceFieldDTO dto = new ScienceFieldDTO();
		mapper.map(entity, dto);
		return dto;
	}

	@Override
	public ScienceField DtoToEntity(ScienceFieldDTO dto) {
		ScienceField entity = new ScienceField();
		mapper.map(dto, entity);
		return entity;
	}


}
