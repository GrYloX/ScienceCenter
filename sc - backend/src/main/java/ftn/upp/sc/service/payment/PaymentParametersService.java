package ftn.upp.sc.service.payment;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ftn.upp.sc.config.DatabaseUri;
import ftn.upp.sc.converter.payment.PaymentParametersConverter;
import ftn.upp.sc.dto.payment.PaymentParametersDTO;
import ftn.upp.sc.model.payment.PaymentParameters;
import ftn.upp.sc.repository.payment.PaymentParametersRepository;


@Service
public class PaymentParametersService {
	
	@Autowired
	PaymentParametersRepository paymentParametersRepository;
	
	@Autowired
	PaymentParametersConverter paymentParametersConverter;
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private DatabaseUri databaseUri;
	
	public List<PaymentParameters> findAll() {
		return paymentParametersRepository.findAll();
	}
	
	public PaymentParameters findOne(Long id) {
		Optional<PaymentParameters> paymentParameters = paymentParametersRepository.findById(id);
		return paymentParameters.get();
	}
	
	public PaymentParameters savePaymentParameters(PaymentParametersDTO dto) {
		PaymentParameters paymentParameters = paymentParametersConverter.DtoToEntity(dto);
		/*HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PaymentParametersDTO> entity = new HttpEntity<PaymentParametersDTO>(dto,headers);
		restTemplate.exchange(databaseUri.getKpUri()+"/paymentParameters", HttpMethod.POST,entity,PaymentParametersDTO.class);*/
		return paymentParametersRepository.save(paymentParameters);
	}

	public PaymentParameters deletePaymentParameters(Long id) {
		PaymentParameters paymentParameters = this.findOne(id);
		if(paymentParameters == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant");
		}
		paymentParametersRepository.delete(paymentParameters);
		return paymentParameters;
		
		
	}

}
