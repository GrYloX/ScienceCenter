package ftn.upp.sc.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.upp.sc.dto.EditorDTO;
import ftn.upp.sc.model.Editor;
import ftn.upp.sc.repository.MagazineRepository;
import ftn.upp.sc.repository.ScienceFieldRepository;
import ftn.upp.sc.repository.UserRepository;

@Component
public class EditorConverter implements Converter<Editor, EditorDTO> {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	MagazineRepository magazineRepository;
	
	@Autowired
	ScienceFieldRepository scienceFieldRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public EditorDTO entityToDto(Editor entity) {
		EditorDTO dto = new EditorDTO();
		mapper.map(entity, dto);
		dto.setMagazineIssn(entity.getMagazine().getIssn());
		dto.setScienceField(entity.getScienceField().getId());
		dto.setUserId(entity.getUser().getUsername());
		return dto;
	}

	@Override
	public Editor DtoToEntity(EditorDTO dto) {
		Editor entity = new Editor();
		mapper.map(dto, entity);
		entity.setScienceField(scienceFieldRepository.getOne(dto.getScienceField()));
		entity.setMagazine(magazineRepository.getOne(dto.getMagazineIssn()));
		entity.setUser(userRepository.getOne(dto.getUserId()));
		return entity;
	}

}
