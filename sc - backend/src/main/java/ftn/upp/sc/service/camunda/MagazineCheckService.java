package ftn.upp.sc.service.camunda;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.dto.FormSubmissionDto;
import ftn.upp.sc.model.common.Magazine;
import ftn.upp.sc.model.users.Editor;
import ftn.upp.sc.model.users.User;
import ftn.upp.sc.service.common.MagazineService;

@Service
public class MagazineCheckService implements JavaDelegate {
	
	@Autowired
	MagazineService magazineService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {		
		List<FormSubmissionDto> formSubmission = (List<FormSubmissionDto>)execution.getVariable("formSubmission");
		String magazineIssn = formSubmission.get(0).getFieldValue();
		Magazine m = magazineService.findOne(magazineIssn);		
		execution.setVariable("magazinePaymentType", m.getPaymentType().toString());
		execution.setVariable("magazineIssn", m.getIssn());
		Editor mainEditor = m.getMainEditor();
		User userEditor = mainEditor.getUser();		
		execution.setVariable("mainEditorId", userEditor.getUsername());
	}

}
