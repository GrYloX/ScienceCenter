package ftn.upp.sc.converter.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.upp.sc.converter.Converter;
import ftn.upp.sc.dto.common.MagazineDTO;
import ftn.upp.sc.model.common.Magazine;
import ftn.upp.sc.model.common.ScienceField;
import ftn.upp.sc.model.payment.PaymentOption;
import ftn.upp.sc.model.payment.PaymentParameters;
import ftn.upp.sc.repository.common.ScienceFieldRepository;
import ftn.upp.sc.repository.payment.PaymentOptionRepository;
import ftn.upp.sc.repository.payment.PaymentParametersRepository;
import ftn.upp.sc.repository.user.EditorRepository;

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
		for (Map.Entry<PaymentOption, PaymentParameters> entry : entity.getPaymentOptionParameters().entrySet()) {
		    dto.getPaymentOptionParameters().put(entry.getKey().getId(), entry.getValue().getId());
		}
		
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
		for (Map.Entry<Long, Long> entry : dto.getPaymentOptionParameters().entrySet()) {
		    entity.getPaymentOptionParameters().put(paymentOptionRepository.getOne(entry.getKey()), paymentParametersRepository.getOne(entry.getValue()));
		}
		return entity;
	}


}
