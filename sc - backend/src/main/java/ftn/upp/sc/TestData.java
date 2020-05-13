package ftn.upp.sc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.camunda.bpm.engine.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.upp.sc.dto.ApplicationDTO;
import ftn.upp.sc.dto.EditionDTO;
import ftn.upp.sc.dto.EditorDTO;
import ftn.upp.sc.dto.MagazineDTO;
import ftn.upp.sc.dto.PaperDTO;
import ftn.upp.sc.dto.ReviewerDTO;
import ftn.upp.sc.dto.ScienceFieldDTO;
import ftn.upp.sc.dto.UserDTO;
import ftn.upp.sc.dto.UserDetailsDTO;
import ftn.upp.sc.dto.payment.PaymentOptionDTO;
import ftn.upp.sc.dto.payment.PaymentParametersDTO;
import ftn.upp.sc.model.Application;
import ftn.upp.sc.model.Edition;
import ftn.upp.sc.model.Editor;
import ftn.upp.sc.model.Magazine;
import ftn.upp.sc.model.MagazinePaymentType;
import ftn.upp.sc.model.Paper;
import ftn.upp.sc.model.Reviewer;
import ftn.upp.sc.model.ScienceField;
import ftn.upp.sc.model.User;
import ftn.upp.sc.model.UserDetails;
import ftn.upp.sc.model.payment.PaymentOption;
import ftn.upp.sc.model.payment.PaymentParameters;
import ftn.upp.sc.service.ApplicationService;
import ftn.upp.sc.service.EditionService;
import ftn.upp.sc.service.EditorService;
import ftn.upp.sc.service.MagazineService;
import ftn.upp.sc.service.PaperService;
import ftn.upp.sc.service.ReviewerService;
import ftn.upp.sc.service.ScienceFieldService;
import ftn.upp.sc.service.UserDetailsService;
import ftn.upp.sc.service.UserService;
import ftn.upp.sc.service.payment.PaymentOptionService;
import ftn.upp.sc.service.payment.PaymentParametersService;

@Component
public class TestData {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private EditorService editorService;
	@Autowired
	private ScienceFieldService scienceFieldService;
	@Autowired
	private PaymentParametersService paymentParametersService;
	@Autowired
	private MagazineService	magazineService;
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private EditionService editionService;
	@Autowired
	private PaperService paperService;
	@Autowired
	private PaymentOptionService paymentOptionService;
	@Autowired
	IdentityService identityService;
	@Autowired
	private ReviewerService reviewerService;
	
	@PostConstruct
	private void init(){
		
		identityService.deleteUser("a");
		identityService.deleteUser("b");
		identityService.deleteUser("c");
		identityService.deleteUser("d");
		identityService.deleteUser("s");
		identityService.deleteUser("m");
		identityService.deleteUser("z");
		identityService.deleteUser("x");
		identityService.deleteUser("y");
		
		UserDetailsDTO ud1DTO = new UserDetailsDTO(0, "goranrajic95@gmail.com","Goran","Rajic", "Veternik","Srbija");
		UserDetailsDTO ud2DTO = new UserDetailsDTO(1, "goranrajic95@gmail.com","Valentina","Soldo", "Vrbas","Srbija");
		UserDetailsDTO ud3DTO = new UserDetailsDTO(2, "goranrajic95@gmail.com","Nebojsa","Rajic", "Novi Sad","Srbija");
		UserDetailsDTO ud4DTO = new UserDetailsDTO(3, "goranrajic95@gmail.com","Pera","Peric", "Novi Sad","Srbija");
		UserDetailsDTO ud5DTO = new UserDetailsDTO(4, "goranrajic95@gmail.com","Smiljana","Tedic", "Novi Sad","Srbija");
		UserDetailsDTO ud6DTO = new UserDetailsDTO(5, "goranrajic95@gmail.com","Milana","Carapic", "Novi Sad","Srbija");
		UserDetailsDTO ud7DTO = new UserDetailsDTO(6, "goranrajic95@gmail.com","Mika","Mikic", "Veternik","Srbija");
		UserDetailsDTO ud8DTO = new UserDetailsDTO(7, "goranrajic95@gmail.com","Sima","Simic", "Veternik","Srbija");
		UserDetailsDTO ud9DTO = new UserDetailsDTO(8, "goranrajic95@gmail.com","Bisa","Bisic", "Veternik","Srbija");
		UserDetails ud1 = userDetailsService.saveUserDetails(ud1DTO);
		UserDetails ud2 = userDetailsService.saveUserDetails(ud2DTO);
		UserDetails ud3 = userDetailsService.saveUserDetails(ud3DTO);
		UserDetails ud4 = userDetailsService.saveUserDetails(ud4DTO);
		UserDetails ud5 = userDetailsService.saveUserDetails(ud5DTO);
		UserDetails ud6 = userDetailsService.saveUserDetails(ud6DTO);
		UserDetails ud7 = userDetailsService.saveUserDetails(ud7DTO);
		UserDetails ud8 = userDetailsService.saveUserDetails(ud8DTO);
		UserDetails ud9 = userDetailsService.saveUserDetails(ud9DTO);
		
		UserDTO u1DTO = new UserDTO("a","a",ud1.getId());
		UserDTO u2DTO = new UserDTO("b","b",ud2.getId());
		UserDTO u3DTO = new UserDTO("c","c",ud3.getId());
		UserDTO u4DTO = new UserDTO("d","d",ud4.getId());
		UserDTO u5DTO = new UserDTO("s","s",ud5.getId());
		UserDTO u6DTO = new UserDTO("m","m",ud6.getId());
		UserDTO u7DTO = new UserDTO("z","z",ud7.getId());
		UserDTO u8DTO = new UserDTO("x","x",ud8.getId());
		UserDTO u9DTO = new UserDTO("y","y",ud9.getId());
		User u1 = userService.saveUser(u1DTO);
		User u2 = userService.saveUser(u2DTO);
		User u3 = userService.saveUser(u3DTO);
		User u4 = userService.saveUser(u4DTO);
		User u5 = userService.saveUser(u5DTO);
		User u6 = userService.saveUser(u6DTO);
		User u7 = userService.saveUser(u7DTO);
		User u8 = userService.saveUser(u8DTO);
		User u9 = userService.saveUser(u9DTO);
		
		ScienceFieldDTO sf1DTO = new ScienceFieldDTO("Medicina");
		ScienceFieldDTO sf2DTO = new ScienceFieldDTO("Matematika");
		ScienceFieldDTO sf3DTO = new ScienceFieldDTO("Arhitektura");
		ScienceFieldDTO sf4DTO = new ScienceFieldDTO("Ekonomija");
		ScienceFieldDTO sf5DTO = new ScienceFieldDTO("Tehnologija");
		ScienceField sf1=scienceFieldService.saveScienceField(sf1DTO);
		ScienceField sf2=scienceFieldService.saveScienceField(sf2DTO);
		ScienceField sf3=scienceFieldService.saveScienceField(sf3DTO);
		ScienceField sf4=scienceFieldService.saveScienceField(sf4DTO);
		ScienceField sf5=scienceFieldService.saveScienceField(sf5DTO);		
		
		PaymentOptionDTO po1DTO = new PaymentOptionDTO("credit-card",new ArrayList<String>() {{
		    add("ID");
		    add("Password");
		    add("BankName");
		}});
		PaymentOptionDTO po2DTO = new PaymentOptionDTO("paypal",new ArrayList<String>() {{
		    add("ClientID");
		    add("ClientSecret");
		}});
		PaymentOptionDTO po3DTO = new PaymentOptionDTO("bitcoin",new ArrayList<String>() {{
		    add("Token");
		}});
		PaymentOption po1 = paymentOptionService.savePaymentOption(po1DTO);
		PaymentOption po2 = paymentOptionService.savePaymentOption(po2DTO);
		PaymentOption po3 = paymentOptionService.savePaymentOption(po3DTO);
		
		PaymentParametersDTO pp1DTO = new PaymentParametersDTO(new ArrayList<String>() {{
		    add("111");
		    add("111");
		    add("Bank A");
		}});
		PaymentParametersDTO pp2DTO = new PaymentParametersDTO(new ArrayList<String>() {{
		    add("AURw8OXxtRbGo5d8ya5nv8sN_fByRZxP-EMvl-3HvU7WRYlZuk0hi4_I870UTCVr6aPBH-a_3RZwbFqF");
		    add("EJ_pO6pxtROxCEeNzHEgvBNxxHK9nWrfkjFsj83HyBBDPfMWg_I08PokbsRKJhQLdvAcQmeAsEYGHc6v");
		}});
		PaymentParametersDTO pp3DTO = new PaymentParametersDTO(new ArrayList<String>() {{
		    add("WVZVbwagA7U1kyt-v-NQRK1sHce1-PoosaXzv1Uz");
		}});
		//casopis za pretplatu
		PaymentParametersDTO pp4DTO = new PaymentParametersDTO(new ArrayList<String>() {{
		    add("AURw8OXxtRbGo5d8ya5nv8sN_fByRZxP-EMvl-3HvU7WRYlZuk0hi4_I870UTCVr6aPBH-a_3RZwbFqF");
		    add("EJ_pO6pxtROxCEeNzHEgvBNxxHK9nWrfkjFsj83HyBBDPfMWg_I08PokbsRKJhQLdvAcQmeAsEYGHc6v");
		}});
		PaymentParameters pp1 = paymentParametersService.savePaymentParameters(pp1DTO);
		PaymentParameters pp2 = paymentParametersService.savePaymentParameters(pp2DTO);
		PaymentParameters pp3 = paymentParametersService.savePaymentParameters(pp3DTO);
		PaymentParameters pp4 = paymentParametersService.savePaymentParameters(pp4DTO);
		
		
		Map<Long,Long> paymentOptionParameters1 = new HashMap<Long,Long>();
		paymentOptionParameters1.put((long) 1, pp1.getId());
		paymentOptionParameters1.put((long) 2, pp2.getId());
		paymentOptionParameters1.put((long) 3, pp3.getId());
		Map<Long,Long> paymentOptionParameters2 = new HashMap<Long,Long>();
		paymentOptionParameters2.put((long) 2, pp4.getId());
		
		MagazineDTO m1DTO = new MagazineDTO("0123-4567","Casopis tehnickih nauka",new HashSet<Long>() {{
		    add(sf2.getId());
		    add(sf5.getId());
		}},0.00,MagazinePaymentType.paid_access, paymentOptionParameters1);
		MagazineDTO m2DTO = new MagazineDTO("4567-0123","Casopis medicine",new HashSet<Long>() {{
		    add(sf1.getId());
		}},20.00,MagazinePaymentType.paid_access, paymentOptionParameters2);
		Magazine m1 = magazineService.saveMagazine(m1DTO);
		Magazine m2 = magazineService.saveMagazine(m2DTO);
		
		EditorDTO e1DTO = new EditorDTO(m1.getIssn(),sf5.getId(),"Doktor racunarstva i automatike",u2.getUsername());  //:D
		EditorDTO e2DTO = new EditorDTO(m2.getIssn(),sf1.getId(),"Titula Pere Perica",u4.getUsername());
		Editor e1 = editorService.saveEditor(e1DTO);
		Editor e2 = editorService.saveEditor(e2DTO);
		
		m1.setMainEditor(e1);
		m2.setMainEditor(e2);				
		magazineService.saveMagazine(m1); //ubacio dodatnu metodu da ne mora DTO
		magazineService.saveMagazine(m2);	
		
		EditionDTO ed1DTO = new EditionDTO(m1.getIssn(),20.00, "Prvo izdanje");
		EditionDTO ed2DTO = new EditionDTO(m1.getIssn(),20.00, "Drugo izdanje");
		EditionDTO ed3DTO = new EditionDTO(m2.getIssn(),0.00, "Prvo izdanje");
		Edition ed1 = editionService.saveEdition(ed1DTO);
		Edition ed2 = editionService.saveEdition(ed2DTO);
		Edition ed3 = editionService.saveEdition(ed3DTO);
		
		ApplicationDTO a1DTO = new ApplicationDTO("SOAP web servisi u sistemu za pretragu "
				+ "i rezervisanje smestaja", "U radu se opisuje primena SOAP veb servisa u ", u1.getUsername(), 
						"Veb aplikacije, SOAP, Angular",m1.getIssn(), sf5.getId(), "Diplomski RA96-2014.doc");
		
		ApplicationDTO a2DTO = new ApplicationDTO("Poredjenje programskih jezika C++ i C#","U ovom radu", u6.getUsername(), 
						"Objektno-orijentisani jezik, C++, C#",m1.getIssn(), sf5.getId(), 
						"RA882014_v2.doc");
		ApplicationDTO a3DTO = new ApplicationDTO("Implementacija veb aplikacije za podrsku"
				+ " rada turisticke agencije "
				+ "u programskom jeziku Java","U radu je opisana izrada ", u5.getUsername(), 
						"Java, Web servisi, Spring boot",m1.getIssn(), sf5.getId(), 
						"SMDIPL-converted-17714239.doc");
		Application a1 = applicationService.saveApplication(a1DTO);
		Application a2 = applicationService.saveApplication(a2DTO);
		Application a3 = applicationService.saveApplication(a3DTO);
		
		PaperDTO p1DTO = new PaperDTO("id1",a1.getId(),20.00,ed1.getId());
		PaperDTO p2DTO = new PaperDTO("id2",a2.getId(),20.00,ed1.getId());
		PaperDTO p3DTO = new PaperDTO("id3",a3.getId(),20.00,ed2.getId());
		Paper p1 = paperService.savePaper(p1DTO);
		Paper p2 = paperService.savePaper(p2DTO);
		Paper p3 = paperService.savePaper(p3DTO);
		
		ReviewerDTO r1DTO = new ReviewerDTO(new HashSet<Long>() {{
		    add(sf2.getId());
		    add(sf4.getId());
		    add(sf5.getId());
		}}, "Neka titula",u7.getUsername());
		ReviewerDTO r2DTO = new ReviewerDTO(new HashSet<Long>() {{
		    add(sf2.getId());
		    add(sf3.getId());
		    add(sf4.getId());
		}}, "Neka titula",u8.getUsername());
		ReviewerDTO r3DTO = new ReviewerDTO(new HashSet<Long>() {{
		    add(sf1.getId());
		    add(sf4.getId());
		    add(sf5.getId());
		}}, "Neka titula",u9.getUsername());
		ReviewerDTO r4DTO = new ReviewerDTO(new HashSet<Long>() {{
		    add(sf1.getId());
		    add(sf4.getId());
		    add(sf5.getId());
		}}, "Neka titula",u3.getUsername());
		Reviewer r1 = reviewerService.saveReviewer(r1DTO);
		Reviewer r2 = reviewerService.saveReviewer(r2DTO);
		Reviewer r3 = reviewerService.saveReviewer(r3DTO);
		Reviewer r4 = reviewerService.saveReviewer(r4DTO);
		r1.getMagazines().add(m1);
		r1.getMagazines().add(m2);
		r2.getMagazines().add(m1);
		r3.getMagazines().add(m2);
		r4.getMagazines().add(m1);
		reviewerService.save(r1);
		reviewerService.save(r2);
		reviewerService.save(r3);
		reviewerService.save(r4);
		
	}

}
