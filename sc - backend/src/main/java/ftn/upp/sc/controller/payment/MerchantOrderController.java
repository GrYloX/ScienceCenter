package ftn.upp.sc.controller.payment;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import ftn.upp.sc.config.DatabaseUri;
import ftn.upp.sc.dto.payment.MerchantOrderDTO;
import ftn.upp.sc.dto.payment.ResponseDTO;
import ftn.upp.sc.model.TransactionState;
import ftn.upp.sc.model.payment.MerchantOrder;
import ftn.upp.sc.repository.MagazineRepository;
import ftn.upp.sc.repository.MerchantOrderRepository;
import ftn.upp.sc.repository.UserRepository;


@Controller
@RequestMapping(value="/merchantOrderi")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 13200)
public class MerchantOrderController {

	@Autowired
	MerchantOrderRepository merchantOrderRepository;
	@Autowired
	MagazineRepository magazineRepository;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private DatabaseUri databaseUri;
	@Autowired
	UserRepository userRepository;
	
	
	@RequestMapping(value = "sacuvajMerchantOrdera", method = RequestMethod.GET)
    public String initCreationForm() {        
        return "Bleja zivotna";
    }
	
	 @RequestMapping(value = "sacuvajMerchantOrdera", method = RequestMethod.POST, consumes="application/json")
	 public ResponseEntity<ResponseDTO> processCreationForm(@RequestBody MerchantOrderDTO moDTO) {
		MerchantOrder mo = new MerchantOrder();
    	mo.setAmountOrder(moDTO.getAmountOrder());
    	mo.setMerchant(magazineRepository.getOne(moDTO.getMerchant_id()));
    	mo.setSuccessUrl(moDTO.getSuccessUrl());
    	mo.setFailedUrl(moDTO.getFailedUrl());
    	mo.setErrorUrl(moDTO.getErrorUrl());
    	mo.setTransactionState(TransactionState.U_PROCESU);
    	mo.setMerchantTimestamp(new Date());
    	mo.setBuyer(userRepository.getOne(moDTO.getBuyer_id()));
    	mo.setType(moDTO.getType());
    	mo.setPurchaseTypeId(moDTO.getPurchaseTypeId());
    	mo.setMerchantOrderID(moDTO.getMerchantOrderID());
    	moDTO.setMerchantTimestamp(new Date());
    	moDTO.setTransactionState(TransactionState.U_PROCESU);
    	merchantOrderRepository.save(mo);
        ResponseEntity<String> re1 = restTemplate.postForEntity(databaseUri.getKpUri()+"/merchantOrderi/sacuvajMerchantOrdera", moDTO, String.class);
        ResponseDTO responseDTO = new ResponseDTO(databaseUri.getKpUri()+"/"+re1.getBody());
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);     
	 }
	 
	 @RequestMapping(value = "finish/{merchantOrderId}", method = RequestMethod.POST)
		public ResponseEntity<String> finishOrder(@RequestBody Boolean success, @PathVariable String merchantOrderId){
			if(success){
				MerchantOrder mo = merchantOrderRepository.getOne(merchantOrderId);
				mo.setTransactionState(ftn.upp.sc.model.TransactionState.USPEH);
				merchantOrderRepository.save(mo);
				return new ResponseEntity<String>(mo.getSuccessUrl(), HttpStatus.OK);
			}
			else{
				return new ResponseEntity<String>(HttpStatus.OK); //kada ne valja nesto izmeniti ovde
			}
			
		}
	 
	 @RequestMapping(value = "subscribe", method = RequestMethod.POST, consumes="application/json")
	 public ResponseEntity<ResponseDTO> subscribe(@RequestBody MerchantOrderDTO moDTO) {
		 MerchantOrder mo = new MerchantOrder();
    	 mo.setAmountOrder(moDTO.getAmountOrder());
    	 mo.setMerchant(magazineRepository.getOne(moDTO.getMerchant_id()));
    	 mo.setSuccessUrl(moDTO.getSuccessUrl());
    	 mo.setFailedUrl(moDTO.getFailedUrl());
    	 mo.setErrorUrl(moDTO.getErrorUrl());
    	 mo.setTransactionState(TransactionState.U_PROCESU);
    	 mo.setMerchantTimestamp(new Date());
    	 mo.setBuyer(userRepository.getOne(moDTO.getBuyer_id()));
    	 mo.setType(moDTO.getType());
    	 mo.setMerchantOrderID(moDTO.getMerchantOrderID());
    	 merchantOrderRepository.save(mo);
    	 moDTO.setMerchantTimestamp(new Date());
     	 moDTO.setTransactionState(TransactionState.U_PROCESU);
     	 ResponseEntity<String> re1 = restTemplate.postForEntity(databaseUri.getKpUri()+"/merchantOrderi/sacuvajMerchantOrdera", moDTO, String.class);
     	 ResponseDTO responseDTO = new ResponseDTO(databaseUri.getKpUri()+"/paypal/subscription/create/"+mo.getMerchantOrderID());
     	 return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
	 }
   
    
   
	 
}



	
	
	

