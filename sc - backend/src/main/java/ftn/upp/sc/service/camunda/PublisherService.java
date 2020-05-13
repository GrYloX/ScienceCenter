package ftn.upp.sc.service.camunda;

import java.util.List;
import java.util.UUID;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.dto.EditionDTO;
import ftn.upp.sc.dto.PaperDTO;
import ftn.upp.sc.model.Application;
import ftn.upp.sc.model.Edition;
import ftn.upp.sc.model.Paper;
import ftn.upp.sc.service.ApplicationService;
import ftn.upp.sc.service.EditionService;
import ftn.upp.sc.service.PaperService;

@Service
public class PublisherService implements JavaDelegate {

	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	EditionService editionService;
	
	@Autowired
	PaperService paperService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {		
		Long applicationId = (Long) runtimeService.getVariable(execution.getProcessInstanceId(),"applicationId");
		Application app = applicationService.findOne(applicationId);
		boolean added = false;
		String magazineIssn = (String) execution.getVariable("magazineIssn");
		List<Edition> editions = editionService.findByMagazineIssn(magazineIssn);
		for(Edition ed : editions){
			List<Paper> papers = paperService.findByEditionId(ed.getId());
			if(papers.size() < 3){
				added = true;
				PaperDTO newPaper = new PaperDTO();
				newPaper.setDoi(UUID.randomUUID().toString());
				newPaper.setEditionId(ed.getId());
				newPaper.setPaperDetailsId(app.getId());
				newPaper.setPrice(20.00);
				paperService.savePaper(newPaper);
				break;
			}
		}
		if(!added){
			EditionDTO newEdDto = new EditionDTO(magazineIssn,20.00, "Najnovije izdanje");
			Edition newEd = editionService.saveEdition(newEdDto);
			PaperDTO newPaper = new PaperDTO();
			newPaper.setDoi(UUID.randomUUID().toString());
			newPaper.setEditionId(newEd.getId());
			newPaper.setPaperDetailsId(app.getId());
			newPaper.setPrice(20.00);
			paperService.savePaper(newPaper);			
		}
		
	}

}
