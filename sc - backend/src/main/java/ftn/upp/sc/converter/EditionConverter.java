package ftn.upp.sc.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.upp.sc.dto.EditionDTO;
import ftn.upp.sc.model.Edition;
import ftn.upp.sc.repository.MagazineRepository;

@Component
public class EditionConverter implements Converter<Edition, EditionDTO> {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	MagazineRepository magazineRepository;
	
	@Override
	public EditionDTO entityToDto(Edition entity) {
		EditionDTO dto = new EditionDTO();
		mapper.map(entity, dto);
		dto.setMagazineIssn(entity.getMagazine().getIssn());
		return dto;
	}

	@Override
	public Edition DtoToEntity(EditionDTO dto) {
		Edition entity = new Edition();
		mapper.map(dto, entity);
		entity.setMagazine(magazineRepository.getOne(dto.getMagazineIssn()));
		return entity;
	}


}
