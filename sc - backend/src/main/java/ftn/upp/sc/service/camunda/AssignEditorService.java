package ftn.upp.sc.service.camunda;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import ftn.upp.sc.dto.ApplicationDTO;
import ftn.upp.sc.model.Editor;
import ftn.upp.sc.model.Magazine;
import ftn.upp.sc.model.Reviewer;
import ftn.upp.sc.model.ScienceField;
import ftn.upp.sc.model.User;
import ftn.upp.sc.model.UserDetails;
import ftn.upp.sc.repository.EditorRepository;
import ftn.upp.sc.repository.ReviewerRepository;
import ftn.upp.sc.repository.ScienceFieldRepository;
import ftn.upp.sc.service.ApplicationService;
import ftn.upp.sc.service.MagazineService;
import ftn.upp.sc.service.UserService;

@Service
public class AssignEditorService implements JavaDelegate {
	
	@Autowired
	MagazineService magazineService;

	@Autowired
	EditorRepository editorRepository;
	
	@Autowired
	ReviewerRepository reviewerRepository;
	
	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	ScienceFieldRepository scienceFieldRepository;
	
	@Autowired
	UserService	userService;
	
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private Environment env;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {				
		String magazineIssn = (String) execution.getVariable("magazineIssn");		
		Long applicationId = (Long) execution.getVariable("applicationId");
		ApplicationDTO app = applicationService.findOneDto(applicationId);
		Long sfId = app.getScienceFieldId();
		ScienceField sf = scienceFieldRepository.findById(sfId).get();
		Magazine m = magazineService.findOne(magazineIssn);
		List<Reviewer> reviewersOfSf = reviewerRepository.findByMagazinesContainingAndFieldsContaining(m, sf);
		if(reviewersOfSf.isEmpty()){
			String mainEditorId = (String) execution.getVariable("mainEditorId");
			execution.setVariable("assignedEditorId", mainEditorId);
			User mainEditor = userService.findOne(mainEditorId);
			UserDetails mainEditorDetails = mainEditor.getUserDetails();
			SimpleMailMessage mail1 = new SimpleMailMessage();
			mail1.setTo(mainEditorDetails.getEmail());
			mail1.setFrom(env.getProperty("spring.mail.username"));
			mail1.setSubject("Novi rad");
			mail1.setText("Pozdrav " + mainEditorDetails.getFirstName() + ",\n\n Za naucnu oblast u "
					+ "kojoj je prijavljen novi rad nije pronadjen nijedan recenzent u casopisu koji uredjujete."
					+ "Potrebno"
					+ " je da odaberete recenzente koji ce obaviti recenziju novog rada koji"
					+ " je prijavljen.");
			javaMailSender.send(mail1);
			System.out.println("Email poslat!");
		}
		else{
			List<Editor> editors = editorRepository.findByMagazine_issnAndScienceField_id(magazineIssn, sfId);
			if(!editors.isEmpty()){
				Editor editor = editors.get(0);
				User editorCredentials = editor.getUser();
				UserDetails editorUserDetails = editorCredentials.getUserDetails();
				execution.setVariable("assignedEditorId", editorCredentials.getUsername());
				SimpleMailMessage mail1 = new SimpleMailMessage();
				mail1.setTo(editorUserDetails.getEmail());
				mail1.setFrom(env.getProperty("spring.mail.username"));
				mail1.setSubject("Novi rad");
				mail1.setText("Pozdrav " + editorUserDetails.getFirstName() + ",\n\n Potrebno"
						+ " je da odaberete recenzente koji ce obaviti recenziju novog rada koji"
						+ " je prijavljen.");
				javaMailSender.send(mail1);
				System.out.println("Email poslat!");
			}
			else{
				String mainEditorId = (String) execution.getVariable("mainEditorId");
				execution.setVariable("assignedEditorId", mainEditorId);
				User mainEditor = userService.findOne(mainEditorId);
				UserDetails mainEditorDetails = mainEditor.getUserDetails();
				SimpleMailMessage mail1 = new SimpleMailMessage();
				mail1.setTo(mainEditorDetails.getEmail());
				mail1.setFrom(env.getProperty("spring.mail.username"));
				mail1.setSubject("Novi rad");
				mail1.setText("Pozdrav " + mainEditorDetails.getFirstName() + ",\n\n Za naucnu oblast u "
						+ "kojoj je prijavljen novi rad nije pronadjen nijedan urednik. "
						+ "Potrebno"
						+ " je da odaberete recenzente koji ce obaviti recenziju novog rada koji"
						+ " je prijavljen.");
				javaMailSender.send(mail1);
				System.out.println("Email poslat!");
			}			
		}	
	}

}
