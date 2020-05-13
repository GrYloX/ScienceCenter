package ftn.upp.sc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.ScienceFieldConverter;
import ftn.upp.sc.dto.ScienceFieldDTO;
import ftn.upp.sc.model.ScienceField;
import ftn.upp.sc.repository.ScienceFieldRepository;
import ftn.upp.sc.service.base.ScienceFieldServiceBase;

@Service
public class ScienceFieldService extends ScienceFieldServiceBase {

	public ScienceFieldDTO findOneDto(Long scienceFieldId) {
		Optional<ScienceField> scienceField = scienceFieldRepository.findById(scienceFieldId);
		return scienceFieldConverter.entityToDto(scienceField.get());
	}

}
