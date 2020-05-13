package ftn.upp.sc.service.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.ScienceFieldConverter;
import ftn.upp.sc.dto.ScienceFieldDTO;
import ftn.upp.sc.model.ScienceField;
import ftn.upp.sc.repository.ScienceFieldRepository;

public class ScienceFieldServiceBase {
	
	@Autowired
	public ScienceFieldRepository scienceFieldRepository;
	
	@Autowired
	public ScienceFieldConverter scienceFieldConverter;
	
	public List<ScienceField> findAll() {
		return scienceFieldRepository.findAll();
	}
	
	public ScienceField findOne(long id) {
		Optional<ScienceField> scienceField = scienceFieldRepository.findById(id);
		return scienceField.get();
	}
	
	public ScienceField saveScienceField(ScienceFieldDTO dto) {
		ScienceField scienceField = scienceFieldConverter.DtoToEntity(dto);
		return scienceFieldRepository.save(scienceField);
	}

	public ScienceField deleteScienceField(long id) {
		ScienceField scienceField = this.findOne(id);
		if(scienceField == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant");
		}
		scienceFieldRepository.delete(scienceField);
		return scienceField;
	}
}
