package ftn.upp.sc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.upp.sc.dto.common.ApplicationDTO;
import ftn.upp.sc.dto.common.EditionDTO;
import ftn.upp.sc.dto.common.MagazineDTO;
import ftn.upp.sc.dto.common.PaperDTO;
import ftn.upp.sc.dto.common.ScienceFieldDTO;
import ftn.upp.sc.dto.payment.PaymentOptionDTO;
import ftn.upp.sc.dto.payment.PaymentParametersDTO;
import ftn.upp.sc.dto.user.EditorDTO;
import ftn.upp.sc.dto.user.UserDTO;
import ftn.upp.sc.dto.user.UserDetailsDTO;
import ftn.upp.sc.model.common.Application;
import ftn.upp.sc.model.common.Edition;
import ftn.upp.sc.model.common.Magazine;
import ftn.upp.sc.model.common.Paper;
import ftn.upp.sc.model.common.ScienceField;
import ftn.upp.sc.model.enums.MagazinePaymentType;
import ftn.upp.sc.model.payment.PaymentOption;
import ftn.upp.sc.model.payment.PaymentParameters;
import ftn.upp.sc.model.users.Editor;
import ftn.upp.sc.model.users.User;
import ftn.upp.sc.model.users.UserDetails;
import ftn.upp.sc.service.common.ApplicationService;
import ftn.upp.sc.service.common.EditionService;
import ftn.upp.sc.service.common.MagazineService;
import ftn.upp.sc.service.common.PaperService;
import ftn.upp.sc.service.common.ScienceFieldService;
import ftn.upp.sc.service.payment.PaymentOptionService;
import ftn.upp.sc.service.payment.PaymentParametersService;
import ftn.upp.sc.service.user.EditorService;
import ftn.upp.sc.service.user.UserDetailsService;
import ftn.upp.sc.service.user.UserService;

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
	
	
	
	@PostConstruct
	private void init(){
		
		UserDetailsDTO ud1DTO = new UserDetailsDTO(0, "goranrajic95@gmail.com","Goran","Rajic", "Veternik","Srbija");
		UserDetailsDTO ud2DTO = new UserDetailsDTO(0, "valentinasoldo94@outlook.com","Valentina","Soldo", "Vrbas","Srbija");
		UserDetailsDTO ud3DTO = new UserDetailsDTO(0, "nebojsarajic92@yahoo.com","Nebojsa","Rajic", "Novi Sad","Srbija");
		UserDetailsDTO ud4DTO = new UserDetailsDTO(0, "peraperic95@yahoo.com","Pera","Peric", "Novi Sad","Srbija");
		UserDetailsDTO ud5DTO = new UserDetailsDTO(0, "smiljanatedic@gmail.com","Smiljana","Tedic", "Novi Sad","Srbija");
		UserDetailsDTO ud6DTO = new UserDetailsDTO(0, "milanacarapic95@gmail.com","Milana","Carapic", "Novi Sad","Srbija");
		UserDetails ud1 = userDetailsService.saveUserDetails(ud1DTO);
		UserDetails ud2 = userDetailsService.saveUserDetails(ud2DTO);
		UserDetails ud3 = userDetailsService.saveUserDetails(ud3DTO);
		UserDetails ud4 = userDetailsService.saveUserDetails(ud4DTO);
		UserDetails ud5 = userDetailsService.saveUserDetails(ud5DTO);
		UserDetails ud6 = userDetailsService.saveUserDetails(ud6DTO);
		
		UserDTO u1DTO = new UserDTO("a","a",ud1.getId());
		UserDTO u2DTO = new UserDTO("b","b",ud2.getId());
		UserDTO u3DTO = new UserDTO("c","c",ud3.getId());
		UserDTO u4DTO = new UserDTO("d","d",ud4.getId());
		UserDTO u5DTO = new UserDTO("s","s",ud5.getId());
		UserDTO u6DTO = new UserDTO("m","m",ud6.getId());
		User u1 = userService.saveUser(u1DTO);
		User u2 = userService.saveUser(u2DTO);
		User u3 = userService.saveUser(u3DTO);
		User u4 = userService.saveUser(u4DTO);
		User u5 = userService.saveUser(u5DTO);
		User u6 = userService.saveUser(u6DTO);
		
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
		}},20.00,MagazinePaymentType.open_access, paymentOptionParameters2);
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
				+ "i rezervisanje smestaja", "U radu se opisuje primena SOAP veb servisa u "
						+ "komunikaciji razlicitih modula u sistemu za pretragu"
						+ " i rezervaciju smestaja. Objašnjeno je koje su potrebe "
						+ "sistema i koje su tehnologije izabrane u implementaciji "
						+ "modula sistema. Opisan je model podataka i objašnjeni "
						+ "su neki od detalja implementacije.", u1.getUsername(), 
						"Veb aplikacije, SOAP, Angular",m1.getIssn(), sf5.getId(), "Diplomski RA96-2014.doc");
		
		ApplicationDTO a2DTO = new ApplicationDTO("Poredjenje programskih jezika C++ i C#","U ovom radu"
				+ " se vrsi poredjenje programskih jezika C++ i C#. Za potrebe rada bilo je neophodno "
				+ "detaljno analizirati sve osobine i elemente programskih jezika pojedinacno,"
				+ " medjusobno ih uporediti i utvrditi u kojoj meri se podudaraju.", u6.getUsername(), 
						"Objektno-orijentisani jezik, C++, C#",m1.getIssn(), sf5.getId(), 
						"RA882014_v2.doc");
		ApplicationDTO a3DTO = new ApplicationDTO("Implementacija veb aplikacije za podrsku"
				+ " rada turisticke agencije "
				+ "u programskom jeziku Java","U radu je opisana izrada "
						+ "veb aplikacije za podršku rada turisticke agencije."
						+ " Modeliranje je dato preko UML dijagrama, "
						+ "a aplikacija je implementirana koriscenjem "
						+ "Java programskog jezika i Eclipse razvojnog okruzenja.", u5.getUsername(), 
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
		
		
	}

}
