package ftn.upp.sc.converter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.upp.sc.dto.MagazineDTO;
import ftn.upp.sc.model.Magazine;
import ftn.upp.sc.model.ScienceField;
import ftn.upp.sc.model.payment.PaymentOption;
import ftn.upp.sc.model.payment.PaymentParameters;
import ftn.upp.sc.repository.EditorRepository;
import ftn.upp.sc.repository.ScienceFieldRepository;
import ftn.upp.sc.repository.payment.PaymentOptionRepository;
import ftn.upp.sc.repository.payment.PaymentParametersRepository;

@Component
public class MagazineConverter implements Converter<Magazine, MagazineDTO> {

	private ModelMapper mapper;
	
	@Autowired
	PaymentOptionRepository paymentOptionRepository;
	
	@Autowired
	PaymentParametersRepository paymentParametersRepository;
	
	@Autowired
	ScienceFieldRepository scienceFieldRepository;
	
	@Autowired
	EditorRepository editorRepository;

	@Autowired
    public MagazineConverter(ModelMapper modelMapper){
		this.mapper = modelMapper;
	    this.mapper.addMappings(skipModifiedFieldsMap);
    }
	
	PropertyMap<Magazine, MagazineDTO> skipModifiedFieldsMap = new PropertyMap<Magazine, MagazineDTO>() {
	      protected void configure() {
	    	 skip(destination.getScienceFields());
	         skip(destination.getPaymentOptionParameters());
	         skip(destination.getMainEditorId());
	         skip(destination.getMainEditorFirstName());
	         skip(destination.getMainEditorLastName());
	     }
	};
	
	@Override
	public MagazineDTO entityToDto(Magazine entity) {
		MagazineDTO dto = new MagazineDTO();
		mapper.map(entity, dto);
		for(ScienceField sf : entity.getScienceFields()){
			dto.getScienceFields().add(sf.getId());
			dto.getScienceFieldNames().add(sf.getName());
		}	
		dto.setMainEditorId(entity.getMainEditor().getId());
		dto.setMainEditorFirstName(entity.getMainEditor().getUser().getUserDetails().getFirstName());
		dto.setMainEditorLastName(entity.getMainEditor().getUser().getUserDetails().getLastName());
		
		return dto;
	}

	@Override
	public Magazine DtoToEntity(MagazineDTO dto) {
		Magazine entity = new Magazine();
		mapper.map(dto, entity);
		for(Long sf : dto.getScienceFields()){
			entity.getScienceFields().add(scienceFieldRepository.getOne(sf));
		}
		if(dto.getMainEditorId()!=null){
			entity.setMainEditor(editorRepository.getOne(dto.getMainEditorId()));
		}		
		
		return entity;
	}


}
