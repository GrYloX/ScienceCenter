package ftn.upp.sc.service.camunda;

import java.util.List;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

public class ReviewersListCheckService implements JavaDelegate {
	
	@Autowired
	RuntimeService runtimeService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		List<String> reviewers = (List<String>) execution.getVariable("reviewers");
		if(reviewers.isEmpty()){
			execution.setVariable("reviewsDone", true);
		}
		else{
			execution.setVariable("reviewsDone", false);
		}
	}

}
