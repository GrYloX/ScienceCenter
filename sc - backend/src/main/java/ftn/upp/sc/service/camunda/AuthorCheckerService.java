package ftn.upp.sc.service.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class AuthorCheckerService implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {		

	}

}
