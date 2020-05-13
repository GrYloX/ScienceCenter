package ftn.upp.sc.service.camunda;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.dto.FormSubmissionDto;
import ftn.upp.sc.dto.UserDTO;
import ftn.upp.sc.dto.UserDetailsDTO;
import ftn.upp.sc.model.UserDetails;
import ftn.upp.sc.service.UserDetailsService;
import ftn.upp.sc.service.UserService;

@Service
public class RegisterService implements JavaDelegate {
	
	@Autowired
	UserService userService;
	
	@Autowired	
	UserDetailsService userDetailsService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {		
		List<FormSubmissionDto> formSubmission = (List<FormSubmissionDto>)execution.getVariable("formSubmission");
		UserDetailsDTO ud = new UserDetailsDTO();
		UserDTO user = new UserDTO();
		for(FormSubmissionDto dto : formSubmission){
			if(dto.getFieldId().equals("username")) {
				user.setUsername(dto.getFieldValue());
			}
			else if(dto.getFieldId().equals("password")) {
				user.setPassword(dto.getFieldValue());
			}
			else if(dto.getFieldId().equals("firstName")) {
				ud.setFirstName(dto.getFieldValue());
			}
			else if(dto.getFieldId().equals("lastName")) {
				ud.setLastName(dto.getFieldValue());
			}
			else if(dto.getFieldId().equals("city")) {
				ud.setCity(dto.getFieldValue());
			}
			else if(dto.getFieldId().equals("country")) {
				ud.setCountry(dto.getFieldValue());
			}
			else if(dto.getFieldId().equals("email")) {
				ud.setEmail(dto.getFieldValue());
			}
		}
		UserDetails udd = userDetailsService.saveUserDetails(ud);
		user.setUserDetailsId(udd.getId());
		userService.saveUser(user);
		
		

	}

}
