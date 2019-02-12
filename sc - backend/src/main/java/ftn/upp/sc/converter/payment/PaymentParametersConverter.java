package ftn.upp.sc.converter.payment;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.upp.sc.converter.Converter;
import ftn.upp.sc.dto.payment.PaymentParametersDTO;
import ftn.upp.sc.model.payment.PaymentParameters;
import ftn.upp.sc.repository.payment.PaymentOptionRepository;

@Component
public class PaymentParametersConverter implements Converter<PaymentParameters, PaymentParametersDTO> {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	PaymentOptionRepository paymentOptionRepository;
	
	@Override
	public PaymentParametersDTO entityToDto(PaymentParameters entity) {
		PaymentParametersDTO dto = new PaymentParametersDTO();
		mapper.map(entity, dto);
		return dto;
	}

	@Override
	public PaymentParameters DtoToEntity(PaymentParametersDTO dto) {
		PaymentParameters entity = new PaymentParameters();
		mapper.map(dto, entity);
		return entity;
	}

}
