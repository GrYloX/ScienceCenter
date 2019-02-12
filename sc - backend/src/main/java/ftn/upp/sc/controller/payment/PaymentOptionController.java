package ftn.upp.sc.controller.payment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ftn.upp.sc.converter.payment.PaymentOptionConverter;
import ftn.upp.sc.dto.payment.PaymentOptionDTO;
import ftn.upp.sc.model.payment.PaymentOption;
import ftn.upp.sc.service.payment.PaymentOptionService;

@Controller
@RequestMapping(value="/paymentOption")
@CrossOrigin(origins = "http://localhost:8081", maxAge = 13200)
public class PaymentOptionController {

	@Autowired
	private PaymentOptionService paymentOptionService;
	
	@Autowired
	private PaymentOptionConverter paymentOptionConverter;
	
	@RequestMapping(value="getPaymentOptions", method=RequestMethod.GET)
	public ResponseEntity<List<PaymentOptionDTO>> getPaymentOptions(){
		List<PaymentOption> paymentOptions = paymentOptionService.findAll();
		List<PaymentOptionDTO> paymentOptionsDTO = new ArrayList<PaymentOptionDTO>();
		for(PaymentOption paymentOption : paymentOptions){
			paymentOptionsDTO.add(paymentOptionConverter.entityToDto(paymentOption));
		}
		return new ResponseEntity<>(paymentOptionsDTO, HttpStatus.OK);		
	}	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<PaymentOptionDTO> addPaymentOption(@RequestBody PaymentOptionDTO paymentOptionDTO){
			
		PaymentOption newPaymentOption = paymentOptionService.savePaymentOption(paymentOptionDTO);
		return new ResponseEntity<>(paymentOptionConverter.entityToDto(newPaymentOption), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PaymentOptionDTO> getPaymentOption(@PathVariable Long id) {
		PaymentOption paymentOption = paymentOptionService.findOne(id);
		if (paymentOption == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(paymentOptionConverter.entityToDto(paymentOption), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<PaymentOptionDTO> delete(@PathVariable Long id) {
		PaymentOption deleted = paymentOptionService.deletePaymentOption(id);
		return new ResponseEntity<>(paymentOptionConverter.entityToDto(deleted), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<PaymentOptionDTO> edit(@RequestBody PaymentOptionDTO paymentOptionDTO) {
		PaymentOption edited = paymentOptionService.savePaymentOption(paymentOptionDTO);
		return new ResponseEntity<>(paymentOptionConverter.entityToDto(edited), HttpStatus.OK);
	}
}
