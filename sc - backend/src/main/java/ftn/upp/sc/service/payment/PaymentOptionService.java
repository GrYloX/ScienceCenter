package ftn.upp.sc.service.payment;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.payment.PaymentOptionConverter;
import ftn.upp.sc.dto.payment.PaymentOptionDTO;
import ftn.upp.sc.model.payment.PaymentOption;
import ftn.upp.sc.repository.payment.PaymentOptionRepository;


@Service
public class PaymentOptionService {
	
	@Autowired
	PaymentOptionRepository paymentOptionRepository;
	
	@Autowired
	PaymentOptionConverter paymentOptionConverter;
	
	public List<PaymentOption> findAll() {
		return paymentOptionRepository.findAll();
	}
	
	public PaymentOption findOne(Long id) {
		Optional<PaymentOption> paymentOption = paymentOptionRepository.findById(id);
		return paymentOption.get();
	}
	
	public PaymentOption savePaymentOption(PaymentOptionDTO dto) {
		PaymentOption paymentOption = paymentOptionConverter.DtoToEntity(dto);
		return paymentOptionRepository.save(paymentOption);
	}

	public PaymentOption deletePaymentOption(Long id) {
		PaymentOption paymentOption = this.findOne(id);
		if(paymentOption == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant");
		}
		paymentOptionRepository.delete(paymentOption);
		return paymentOption;
		
		
	}

}
