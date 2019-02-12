package ftn.upp.sc.controller.payment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ftn.upp.sc.converter.payment.PaymentParametersConverter;
import ftn.upp.sc.dto.payment.PaymentParametersDTO;
import ftn.upp.sc.model.payment.PaymentParameters;
import ftn.upp.sc.service.payment.PaymentParametersService;

@Controller
@RequestMapping(value="/paymentParameters")
public class PaymentParametersController {

	@Autowired
	private PaymentParametersService paymentParametersService;
	
	@Autowired
	private PaymentParametersConverter paymentParametersConverter;
	
	@RequestMapping(value="getPaymentParameterss", method=RequestMethod.GET)
	public ResponseEntity<List<PaymentParametersDTO>> getPaymentParameterss(){
		List<PaymentParameters> paymentParameterss = paymentParametersService.findAll();
		List<PaymentParametersDTO> paymentParameterssDTO = new ArrayList<PaymentParametersDTO>();
		for(PaymentParameters paymentParameters : paymentParameterss){
			paymentParameterssDTO.add(paymentParametersConverter.entityToDto(paymentParameters));
		}
		return new ResponseEntity<>(paymentParameterssDTO, HttpStatus.OK);		
	}	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<PaymentParametersDTO> addPaymentParameters(@RequestBody PaymentParametersDTO paymentParametersDTO){
			
		PaymentParameters newPaymentParameters = paymentParametersService.savePaymentParameters(paymentParametersDTO);
		return new ResponseEntity<>(paymentParametersConverter.entityToDto(newPaymentParameters), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PaymentParametersDTO> getPaymentParameters(@PathVariable Long id) {
		PaymentParameters paymentParameters = paymentParametersService.findOne(id);
		if (paymentParameters == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(paymentParametersConverter.entityToDto(paymentParameters), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<PaymentParametersDTO> delete(@PathVariable Long id) {
		PaymentParameters deleted = paymentParametersService.deletePaymentParameters(id);
		return new ResponseEntity<>(paymentParametersConverter.entityToDto(deleted), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<PaymentParametersDTO> edit(@RequestBody PaymentParametersDTO paymentParametersDTO) {
		PaymentParameters edited = paymentParametersService.savePaymentParameters(paymentParametersDTO);
		return new ResponseEntity<>(paymentParametersConverter.entityToDto(edited), HttpStatus.OK);
	}
}
