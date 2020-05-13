package ftn.upp.sc.service.camunda;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.model.Reviewer;
import ftn.upp.sc.model.ScienceField;
import ftn.upp.sc.model.User;
import ftn.upp.sc.repository.ReviewerRepository;

@Service
public class ReviewsCheckService implements JavaDelegate {

	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	ReviewerRepository reviewerRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {		
		List<String> doneReviewers = (List<String>) runtimeService.getVariable(execution.getProcessInstanceId(), "doneReviewers");
		if(doneReviewers==null){
			execution.setVariable("needed", true);
			runtimeService.setVariable(execution.getProcessInstanceId(), "reviewersNeeded", 2);
		}
		else{
			if(doneReviewers.size()>1){
				execution.setVariable("needed", false);				
			}
			else{
				execution.setVariable("needed", true);
				runtimeService.setVariable(execution.getProcessInstanceId(), "reviewersNeeded", 2-doneReviewers.size());
			}
		}		
		runtimeService.setVariable(execution.getProcessInstanceId(), "secondTime", "yes");
		runtimeService.setVariable(execution.getProcessInstanceId(), "reviewers", new ArrayList<String>());
		
	}

}
