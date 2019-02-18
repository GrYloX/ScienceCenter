package ftn.upp.sc.service.common;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.common.ScienceFieldConverter;
import ftn.upp.sc.dto.common.ScienceFieldDTO;
import ftn.upp.sc.model.common.ScienceField;
import ftn.upp.sc.repository.common.ScienceFieldRepository;

@Service
public class ScienceFieldService {
	
	@Autowired
	ScienceFieldRepository scienceFieldRepository;
	
	@Autowired
	ScienceFieldConverter scienceFieldConverter;
	
	public List<ScienceField> findAll() {
		return scienceFieldRepository.findAll();
	}
	
	public ScienceField findOne(Long id) {
		Optional<ScienceField> scienceField = scienceFieldRepository.findById(id);
		return scienceField.get();
	}
	
	public ScienceField saveScienceField(ScienceFieldDTO dto) {
		ScienceField scienceField = scienceFieldConverter.DtoToEntity(dto);
		return scienceFieldRepository.save(scienceField);
	}

	public ScienceField deleteScienceField(Long id) {
		ScienceField scienceField = this.findOne(id);
		if(scienceField == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant");
		}
		scienceFieldRepository.delete(scienceField);
		return scienceField;
		
		
	}

	public ScienceFieldDTO findOneDto(Long scienceFieldId) {
		Optional<ScienceField> scienceField = scienceFieldRepository.findById(scienceFieldId);
		return scienceFieldConverter.entityToDto(scienceField.get());
	}

}
