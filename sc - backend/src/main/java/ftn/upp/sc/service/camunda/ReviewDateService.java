package ftn.upp.sc.service.camunda;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewDateService implements JavaDelegate {

	@Autowired
	RuntimeService runtimeService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {	
		String largeChanges = (String)execution.getVariable("largeChanges");
		if(largeChanges!=null){
			List<String> doneReviewers = (List<String>) runtimeService.getVariable(execution.getProcessInstanceId(), "doneReviewers");
			runtimeService.setVariable(execution.getProcessInstanceId(), "reviewers", doneReviewers);
			runtimeService.setVariable(execution.getProcessInstanceId(), "doneReviewers", null);
		}
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		String date = new SimpleDateFormat("yyyy-MM-dd").format(dt);
		runtimeService.setVariable(execution.getProcessInstanceId(),"reviewDate",date);
	}

}
