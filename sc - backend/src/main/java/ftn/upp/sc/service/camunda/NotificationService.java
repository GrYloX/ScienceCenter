package ftn.upp.sc.service.camunda;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import ftn.upp.sc.dto.ApplicationDTO;
import ftn.upp.sc.dto.FormSubmissionDto;
import ftn.upp.sc.dto.UserDetailsDTO;
import ftn.upp.sc.model.Application;
import ftn.upp.sc.model.User;
import ftn.upp.sc.model.UserDetails;
import ftn.upp.sc.service.ApplicationService;
import ftn.upp.sc.service.UserDetailsService;
import ftn.upp.sc.service.UserService;

@Service
public class NotificationService implements JavaDelegate {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private Environment env;

	@Override
	public void execute(DelegateExecution execution) throws Exception {		
		String message = (String) execution.getVariableLocal("message");
		System.out.println(message);
		if(message.equals("NewPaper")){			
				System.out.println("prodjoh");
				List<FormSubmissionDto> formSubmission = (List<FormSubmissionDto>)execution.getVariable("formSubmission");
				Long applicationId = (Long) execution.getVariable("applicationId");
				System.out.println(applicationId);
				if(applicationId!=null){
					Application existingApplication = applicationService.findOne(applicationId);
					existingApplication.setFile(formSubmission.get(0).getFieldValue());
					applicationService.save(existingApplication);
				}
				else{
					List<List<FormSubmissionDto>> coauthors = (List<List<FormSubmissionDto>>) execution.getVariable("coauthors");
					ApplicationDTO newApplication = new ApplicationDTO();
					if(coauthors!=null){
						for(List<FormSubmissionDto> coauthor : coauthors){
							UserDetailsDTO newCoauthorDTO = new UserDetailsDTO();
							for(FormSubmissionDto field : coauthor){						
								if(field.getFieldId().equals("name")){
									newCoauthorDTO.setFirstName(field.getFieldValue());
								}
								else if(field.getFieldId().equals("lastname")){
									newCoauthorDTO.setLastName(field.getFieldValue());
								}
								else if(field.getFieldId().equals("country")){
									newCoauthorDTO.setCountry(field.getFieldValue());
								}
								else if(field.getFieldId().equals("city")){
									newCoauthorDTO.setCity(field.getFieldValue());
								}
							}
							UserDetails savedCoauthor = userDetailsService.saveUserDetails(newCoauthorDTO);
							newApplication.getCoauthors().add(savedCoauthor.getId());					
						}
					}					
					for(FormSubmissionDto field : formSubmission){
						if(field.getFieldId().equals("title")){
							newApplication.setTitle(field.getFieldValue());
						}
						else if(field.getFieldId().equals("key_terms")){
							newApplication.setKeyTerms(field.getFieldValue());
						}
						else if(field.getFieldId().equals("absract")){
							newApplication.setPaperAbstract(field.getFieldValue());
						}
						else if(field.getFieldId().equals("scientific_area")){
							newApplication.setScienceFieldId(Long.valueOf(field.getFieldValue()));
						}
						else if(field.getFieldId().equals("paper")){
							newApplication.setFile(field.getFieldValue());
						}
					}
					newApplication.setMagazineIssn((String) execution.getVariable("magazineIssn"));
					newApplication.setAuthor((String) execution.getVariable("autorId"));
					Application savedApplication = applicationService.saveApplication(newApplication);	
					System.out.println("stigao do ovde uspeo da sacuvam valjda");
					runtimeService.setVariable(execution.getProcessInstanceId(),"applicationId", savedApplication.getId());					
				}
				String autorId = (String) execution.getVariable("autorId");
				User autor = userService.findOne(autorId);
				UserDetails autorDetails = autor.getUserDetails();
				SimpleMailMessage mail1 = new SimpleMailMessage();
				mail1.setTo(autorDetails.getEmail());
				mail1.setFrom(env.getProperty("spring.mail.username"));
				mail1.setSubject("Prijava rada");
				mail1.setText("Pozdrav " + autorDetails.getFirstName() + ",\n\n Vas rad je poslat na proveru.");
				javaMailSender.send(mail1);
				System.out.println("Email poslat!");
				
				String mainEditorId = (String) execution.getVariable("mainEditorId");
				User mainEditor = userService.findOne(mainEditorId);
				UserDetails mainEditorDetails = mainEditor.getUserDetails();
				SimpleMailMessage mail2 = new SimpleMailMessage();
				mail2.setTo(mainEditorDetails.getEmail());
				mail2.setFrom(env.getProperty("spring.mail.username"));
				mail2.setSubject("Nova prijava rada");
				mail2.setText("Pozdrav " + mainEditorDetails.getFirstName() + ",\n\n Prijavljen je novi rad za vas casopis. Potrebno je izvrsiti proveru aplikacije.");
				javaMailSender.send(mail2);
				System.out.println("Email poslat!");
		}			
		else if(message.equals("NotAppropriate")){			
				String autorId = (String) execution.getVariable("autorId");
				User autor = userService.findOne(autorId);
				UserDetails autorDetails = autor.getUserDetails();
				SimpleMailMessage mail1 = new SimpleMailMessage();
				mail1.setTo(autorDetails.getEmail());
				mail1.setFrom(env.getProperty("spring.mail.username"));
				mail1.setSubject("Prijava rada");
				mail1.setText("Pozdrav " + autorDetails.getFirstName() + ",\n\n Nakon provere Vase prijave zakljuceno je da Vas rad nije tematski prikladan i rad je odbijen.");
				javaMailSender.send(mail1);
				System.out.println("Email poslat!");
		}
		else if(message.equals("CorrectPaper")){
			List<FormSubmissionDto> formSubmission = (List<FormSubmissionDto>)execution.getVariable("formSubmission");
			String comment = "";
			String editDate = "";
			for(FormSubmissionDto field : formSubmission){
				if(field.getFieldId().equals("comment")){
					comment=field.getFieldValue();
				}
				if(field.getFieldId().equals("editDate")){
					editDate=field.getFieldValue();
					System.out.println(editDate);
				}
			}
			String autorId = (String) execution.getVariable("autorId");
			User autor = userService.findOne(autorId);
			UserDetails autorDetails = autor.getUserDetails();
			SimpleMailMessage mail1 = new SimpleMailMessage();
			mail1.setTo(autorDetails.getEmail());
			mail1.setFrom(env.getProperty("spring.mail.username"));
			mail1.setSubject("Prijava rada");
			mail1.setText("Pozdrav " + autorDetails.getFirstName() + ",\n\n Nakon provere Vase prijave"
					+ " zakljuceno je da Vas rad  tematski prikladan, ali rad nije odobren."
					+ " Potrebno je korigovati rad. \n\n Komentar glavnog urednika : "+comment+"\n\n Rok za korigovanje : "+editDate);
			javaMailSender.send(mail1);
			System.out.println("Email poslat!");
		}
		else if(message.equals("PaperRejectedTehnical")){
			String autorId = (String) execution.getVariable("autorId");
			User autor = userService.findOne(autorId);
			UserDetails autorDetails = autor.getUserDetails();
			SimpleMailMessage mail1 = new SimpleMailMessage();
			mail1.setTo(autorDetails.getEmail());
			mail1.setFrom(env.getProperty("spring.mail.username"));
			mail1.setSubject("Prijava rada");
			mail1.setText("Pozdrav " + autorDetails.getFirstName() + ",\n\n Vas rad je odbijen iz tehnickih razloga.");
			javaMailSender.send(mail1);
			System.out.println("Email poslat!");
		}
		else if(message.equals("ReviewsNotFinished")){
			String editorId = (String)execution.getVariable("assignedEditorId");
			User editor = userService.findOne(editorId);
			UserDetails editorDetails = editor.getUserDetails();
			SimpleMailMessage mail1 = new SimpleMailMessage();
			mail1.setTo(editorDetails.getEmail());
			mail1.setFrom(env.getProperty("spring.mail.username"));
			mail1.setSubject("Recenzija rada");
			mail1.setText("Pozdrav " + editorDetails.getFirstName() + ",\n\n Nisu izvrsene sve "
					+ "recenzije u zadatom vremenskom periodu za novi prijavljeni rad."
					+ " Molimo vas da odaberete nove recenzente.");
			javaMailSender.send(mail1);
			System.out.println("Email poslat!");
		}
		else if(message.equals("PaperAccepted")){
			String autorId = (String) execution.getVariable("autorId");
			User autor = userService.findOne(autorId);
			UserDetails autorDetails = autor.getUserDetails();
			SimpleMailMessage mail1 = new SimpleMailMessage();
			mail1.setTo(autorDetails.getEmail());
			mail1.setFrom(env.getProperty("spring.mail.username"));
			mail1.setSubject("Prijava rada");
			mail1.setText("Pozdrav " + autorDetails.getFirstName() + ",\n\n Nakon obavljene"
					+ " recenzije urednik casopisa je odlucio da prihvati vas rad.");
			javaMailSender.send(mail1);
			System.out.println("Email poslat!");
		}
		else if(message.equals("PaperAcceptedSmallerChanges")){
			Date dt = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(dt); 
			c.add(Calendar.DATE, 1);
			dt = c.getTime();		
			String date = new SimpleDateFormat("yyyy-MM-dd").format(dt);
			execution.setVariable("finalEditDate",date);
			String autorId = (String) execution.getVariable("autorId");
			User autor = userService.findOne(autorId);
			UserDetails autorDetails = autor.getUserDetails();
			SimpleMailMessage mail1 = new SimpleMailMessage();
			mail1.setTo(autorDetails.getEmail());
			mail1.setFrom(env.getProperty("spring.mail.username"));
			mail1.setSubject("Prijava rada");
			mail1.setText("Pozdrav " + autorDetails.getFirstName() + ",\n\n Nakon obavljene"
					+ " recenzije urednik casopisa je odlucio da prihvati vasu rad uz male izmene."
					+ " Pogledajte komentare recenzenata u vasoj listi zadataka i posaljite "
					+ "nam ispravljeni rad");
			javaMailSender.send(mail1);
			System.out.println("Email poslat!");
		}
		else if(message.equals("PaperAcceptedBiggerChanges")){
			Date dt = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(dt); 
			c.add(Calendar.DATE, 1);
			dt = c.getTime();		
			String date = new SimpleDateFormat("yyyy-MM-dd").format(dt);
			execution.setVariable("finalEditDate",date);			
			execution.setVariable("largeChanges", "yes");
			String autorId = (String) execution.getVariable("autorId");
			User autor = userService.findOne(autorId);
			UserDetails autorDetails = autor.getUserDetails();
			SimpleMailMessage mail1 = new SimpleMailMessage();
			mail1.setTo(autorDetails.getEmail());
			mail1.setFrom(env.getProperty("spring.mail.username"));
			mail1.setSubject("Prijava rada");
			mail1.setText("Pozdrav " + autorDetails.getFirstName() + ",\n\n Nakon obavljene"
					+ " recenzije urednik casopisa je odlucio da uslovno prihvati vas rad."
					+ " Zbog vecih nedostataka pogledajte komentare recenzenata u vasoj listi "
					+ "zadataka i posaljite"
					+ "nam ispravljeni rad koji ce se opet poslati na recenziju.");
			javaMailSender.send(mail1);
			System.out.println("Email poslat!");
		}
		else if(message.equals("PaperRejected")){
			String autorId = (String) execution.getVariable("autorId");
			User autor = userService.findOne(autorId);
			UserDetails autorDetails = autor.getUserDetails();
			SimpleMailMessage mail1 = new SimpleMailMessage();
			mail1.setTo(autorDetails.getEmail());
			mail1.setFrom(env.getProperty("spring.mail.username"));
			mail1.setSubject("Prijava rada");
			mail1.setText("Pozdrav " + autorDetails.getFirstName() + ",\n\n Nakon obavljene"
					+ " recenzije urednik casopisa je odlucio da odbije vas rad.");
			javaMailSender.send(mail1);
			System.out.println("Email poslat!");			
		}
			
	}

}
