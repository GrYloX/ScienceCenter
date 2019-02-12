package ftn.upp.sc.converter.payment;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.upp.sc.converter.Converter;
import ftn.upp.sc.dto.payment.PaymentOptionDTO;
import ftn.upp.sc.model.payment.PaymentOption;

@Component
public class PaymentOptionConverter implements Converter<PaymentOption, PaymentOptionDTO> {

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public PaymentOptionDTO entityToDto(PaymentOption entity) {
		PaymentOptionDTO dto = new PaymentOptionDTO();
		mapper.map(entity, dto);
		return dto;
	}

	@Override
	public PaymentOption DtoToEntity(PaymentOptionDTO dto) {
		PaymentOption entity = new PaymentOption();
		mapper.map(dto, entity);
		return entity;
	}

}
