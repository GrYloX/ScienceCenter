package ftn.upp.sc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import ftn.upp.sc.config.DatabaseUri;
import ftn.upp.sc.dto.payment.ResponseDTO;

/**
 *  Heeej, PRVO OVO CITAJTE
 *  uputstvo za upotrebu xD
 *  Prvo pogledajte metodu test(), taman usput pogledajte databaseUri klasu, ti uri-ji se nalaze u application.properties podeseni
 *  za sada sam lupio da postoji bankaA i bankaB jos moram da proucim kako cemo 2 instance napraviti, za sada nas to ne zanima
 *  restTemplate koristimo da zovemo metodu sa druge aplikacije, 
 *  da bi se koristio restTemplate morao sam napraviti neku config klasu nista specijalno pogledajte u config paketu, to cete kopirati kad budete
 *  pravili onaj zajednicki pcc
 * 
 *
 */
@RequestMapping("/")
@Controller
public class IndexController {
	
		@Autowired
		private RestTemplate restTemplate;
		
		//Ova klasa sadrzi pocetne uri-je (ono localhost:8181 npr za banku) pogledajte tu klasu ima samo getere i settere (u config paketu je)
		@Autowired
		private DatabaseUri databaseUri;
	
        @RequestMapping(method = RequestMethod.GET)
        public String index() {
            return "index";
        }
        
        @RequestMapping(value="success", method=RequestMethod.GET)
    	public String success(){
        	return "success";
    	}
        @RequestMapping(value="cancel", method=RequestMethod.GET)
    	public String cancel(){
        	return "cancel";
    	}
        
        //Ovo koristimo kada treba da se redirectujemo na drugu aplikaciju (da se prebacimo i na njihov port)
        //Ovo ce nam dobro doci npr kad posaljemo request banci da napravi payment, onda nam treba redirect na njihovu stranicu za unos podataka
        // o kartici
        // A ova funkcija kad je pozovemo se prebaci skroz na front banke i prikaze sve buyere (ukucate na browseru localhost:8081/test)
        @RequestMapping(value="test", method=RequestMethod.GET)
    	public ResponseEntity<ResponseDTO> test(){
        	ResponseDTO r = new ResponseDTO("cao");
        	return new ResponseEntity<ResponseDTO>(r,HttpStatus.OK);
    	}
        
       /*
        * prava upotreba restTemplate se nalazi u merchantControleru u KP (sacuvajMerchanta post metoda), tamo sam napravio da kad sacuva merchanta ovde da posalje zahtev
        * da se sacuva i u banci, pogledajte tamo to
     
     
     
     
     	Ovo ce nam mozda ustrebati mada ne verujem, isto radi sto i gore samo na losiji nacin jer ne promeni port
     	iskulirajte ovo, sad kad pisem ove komentare skontao sam da nam ne treba xD 
     	pogledajte onaj merchantControler
        */ 
        @RequestMapping(value="provera", method=RequestMethod.GET)
        @ResponseBody
    	public String provera(){
    		return restTemplate.getForObject(databaseUri.getBankAUri()+"/buyeri/prikaziBuyera", String.class);
    	}
}
