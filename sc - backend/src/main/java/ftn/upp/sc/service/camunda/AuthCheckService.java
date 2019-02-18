package ftn.upp.sc.service.camunda;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ftn.upp.sc.config.DatabaseUri;
import ftn.upp.sc.dto.FormSubmissionDto;
import ftn.upp.sc.dto.user.UserDTO;
import ftn.upp.sc.model.users.User;


@Service
public class AuthCheckService implements JavaDelegate {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DatabaseUri databaseUri;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {		
		
		List<FormSubmissionDto> formSubmission = (List<FormSubmissionDto>)execution.getVariable("formSubmission");
		if(formSubmission!=null){
			User user = new User();
			for (FormSubmissionDto formField : formSubmission) {
				if(formField.getFieldId().equals("username")) {
					user.setUsername(formField.getFieldValue());
				}
				if(formField.getFieldId().equals("password")) {
					user.setPassword(formField.getFieldValue());
				}
		      }
			restTemplate.postForEntity(databaseUri.getScUri()+"/login", user, String.class);
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.getPrincipal().getClass().equals(String.class)){
			execution.setVariable("prijavljen", false);
		}
		else{
			org.springframework.security.core.userdetails.User authUser =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
			execution.setVariable("prijavljen", true);
			execution.setVariable("autorId", authUser.getUsername());
		}
		
	}

}
