package ftn.upp.sc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ftn.upp.sc.dto.ApplicationDTO;
import ftn.upp.sc.dto.FormFieldsDto;
import ftn.upp.sc.dto.FormSubmissionDto;
import ftn.upp.sc.dto.ReviewDTO;
import ftn.upp.sc.dto.ScienceFieldDTO;
import ftn.upp.sc.dto.TaskDto;
import ftn.upp.sc.dto.payment.ResponseDTO;
import ftn.upp.sc.model.Magazine;
import ftn.upp.sc.model.Reviewer;
import ftn.upp.sc.model.ScienceField;
import ftn.upp.sc.model.User;
import ftn.upp.sc.model.UserDetails;
import ftn.upp.sc.service.ApplicationService;
import ftn.upp.sc.service.MagazineService;
import ftn.upp.sc.service.ReviewerService;
import ftn.upp.sc.service.ScienceFieldService;



@Controller
@RequestMapping(value="/process")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 13200)
public class ProcessController {
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	FormService formService;
	
	@Autowired
	MagazineService magazineService;
	
	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	ScienceFieldService scienceFieldService;
	
	@Autowired
	ReviewerService reviewerService;
	
	@RequestMapping(value="initiate", method=RequestMethod.GET)
	public ResponseEntity<FormFieldsDto> initiateProcess(){
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("upp_process");		
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();	
		properties = checkEnums(properties, pi.getId());
		FormFieldsDto dto = new FormFieldsDto(task.getId(), pi.getId(), properties,task.getName());
        return new ResponseEntity<FormFieldsDto>(dto,HttpStatus.OK);
	}	
	
	@RequestMapping(value="getUserTasks/{username}", method=RequestMethod.GET)
	public ResponseEntity<List<TaskDto>> getUserTasks(@PathVariable String username){
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(username).list();
		List<TaskDto> dtos = new ArrayList<TaskDto>();
		for (Task task : tasks) {
			TaskDto t = new TaskDto(task.getId(), task.getName());
			dtos.add(t);
		}
		return new ResponseEntity<List<TaskDto>>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(value="getTask/{taskId}", method=RequestMethod.GET)
	public ResponseEntity<FormFieldsDto> getTask(@PathVariable String taskId){
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		properties = checkEnums(properties, processInstanceId);
		FormFieldsDto dto = new FormFieldsDto(task.getId(), task.getProcessInstanceId(), properties, task.getName());
        return new ResponseEntity<FormFieldsDto>(dto,HttpStatus.OK);
	}	
	
	@PostMapping(path = "submitTask/{taskId}", produces = "application/json")
    public ResponseEntity<ResponseDTO> post(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();		
		String processInstanceId = task.getProcessInstanceId();
		HashMap<String, Object> map = this.mapListToDto(dto);
		if(task.getName().equals("Unos podataka o koautorima")){
			List<List<FormSubmissionDto>> coauthors = (List<List<FormSubmissionDto>>) runtimeService.getVariable(processInstanceId, "coauthors");
			if(coauthors==null){
				coauthors = new ArrayList<List<FormSubmissionDto>>();
			}
			coauthors.add(dto);
			runtimeService.setVariable(processInstanceId, "coauthors", coauthors);
		}
		else if(task.getName().equals("Odabir recenzenta")){
			List<String> reviewers = (List<String>) runtimeService.getVariable(processInstanceId, "reviewers");
			if(reviewers==null){
				reviewers = new ArrayList<String>();							
			}
			reviewers.add(dto.get(0).getFieldValue());
			runtimeService.setVariable(processInstanceId, "reviewers", reviewers);	
		}
		else if(task.getName().equals("Recenzija rada")){
			List<List<FormSubmissionDto>> reviews = (List<List<FormSubmissionDto>>) runtimeService.getVariable(processInstanceId, "reviews");
			if(reviews==null){
				reviews = new ArrayList<List<FormSubmissionDto>>();
			}
			reviews.add(dto);
			runtimeService.setVariable(processInstanceId, "reviews", reviews);
			List<String> reviewers = (List<String>) runtimeService.getVariable(processInstanceId, "reviewers");			
			reviewers.remove(task.getAssignee());
			runtimeService.setVariable(processInstanceId, "reviewers", reviewers);
			List<String> doneReviewers = (List<String>) runtimeService.getVariable(processInstanceId, "doneReviewers");		
			if(doneReviewers==null){
				doneReviewers = new ArrayList<String>();
			}
			doneReviewers.add(task.getAssignee());
			runtimeService.setVariable(processInstanceId, "doneReviewers", doneReviewers);
		}
		else{
			runtimeService.setVariable(processInstanceId, "formSubmission", dto);
		}
		formService.submitTaskForm(taskId, map);
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
		ResponseDTO response;
		if(tasks.isEmpty()){
			response = new ResponseDTO("");
		}
		else{
			Task task1 = tasks.get(0);
			response = new ResponseDTO(task1.getId());	
		}				
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }
	
	@RequestMapping(value="getApplication/{taskId}", method=RequestMethod.GET)
	public ResponseEntity<ApplicationDTO> getApplication(@PathVariable String taskId){
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		Long applicationId = (Long) runtimeService.getVariable(processInstanceId,"applicationId");
		ApplicationDTO dto = applicationService.findOneDto(applicationId);
        return new ResponseEntity<ApplicationDTO>(dto,HttpStatus.OK);
	}	
	
	@RequestMapping(value="getScienceField/{taskId}", method=RequestMethod.GET)
	public ResponseEntity<ScienceFieldDTO> getReviewersOfField(@PathVariable String taskId){
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		Long applicationId = (Long) runtimeService.getVariable(processInstanceId,"applicationId");
		ApplicationDTO dto = applicationService.findOneDto(applicationId);
		ScienceFieldDTO sf = scienceFieldService.findOneDto(dto.getScienceFieldId());		
        return new ResponseEntity<ScienceFieldDTO>(sf,HttpStatus.OK);
	}	
	
	@RequestMapping(value="getReviews/{taskId}", method=RequestMethod.GET)
	public ResponseEntity<List<ReviewDTO>> getReviews(@PathVariable String taskId){
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		List<List<FormSubmissionDto>> reviews = (List<List<FormSubmissionDto>>) runtimeService.getVariable(processInstanceId, "reviews");	
		List<ReviewDTO> listDto = new ArrayList<ReviewDTO>();
		for(List<FormSubmissionDto> list : reviews){
			ReviewDTO rDto = new ReviewDTO();
			for(FormSubmissionDto dto : list){
				if(dto.getFieldId().equals("commentForAuthor")){
					rDto.setCommentForAuthor(dto.getFieldValue());
				}
				else if(dto.getFieldId().equals("commentForEditor")){
					rDto.setCommentForEditor(dto.getFieldValue());
				}
				else if(dto.getFieldId().equals("recommendation")){
					if(dto.getFieldValue().equals("val_1")){
						rDto.setRecommendation("Prihvatiti");
					}
					else if(dto.getFieldValue().equals("val_2")){
						rDto.setRecommendation("Prihvatiti uz manje ispravke");
					}
					else if(dto.getFieldValue().equals("val_3")){
						rDto.setRecommendation("Uslovno prihvatiti uz veće ispravke");
					}
					else if(dto.getFieldValue().equals("val_4")){
						rDto.setRecommendation("Odbiti");
					}
				}
			}
			listDto.add(rDto);
		}
        return new ResponseEntity<List<ReviewDTO>>(listDto,HttpStatus.OK);
	}	
	
	@RequestMapping(value="registerSignal/{taskId}", method=RequestMethod.GET)
	public ResponseEntity<FormFieldsDto> registerSignal(@PathVariable String taskId){
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		String executionId = task.getExecutionId();
		runtimeService.signalEventReceived("user_wants_registration_signal", executionId);
		Task task1 = taskService.createTaskQuery().processInstanceId(processInstanceId).list().get(0);
		TaskFormData tfd = formService.getTaskFormData(task1.getId());
		List<FormField> properties = tfd.getFormFields();
		properties = checkEnums(properties, processInstanceId);
		FormFieldsDto dto = new FormFieldsDto(task1.getId(), task1.getProcessInstanceId(), properties, task1.getName());
        return new ResponseEntity<>(dto, HttpStatus.OK);
	}	
	
	
	private List<FormField> checkEnums(List<FormField> properties, String processInstanceId){
		for(FormField fp : properties) {
			if(fp.getTypeName().equals("enum")){
				if(fp.getId().equals("casopisSelect")){
					List<Magazine> magazines = magazineService.findAll();					
					for(Magazine m : magazines){						
						fp.getProperties().put(m.getIssn(), m.getName());
					}
				}
				if(fp.getId().equals("scientific_area")){
					String magazineIssn = (String) runtimeService.getVariable(processInstanceId,"magazineIssn");
					Magazine m = magazineService.findOne(magazineIssn);
					for(ScienceField sf : m.getScienceFields()){
						fp.getProperties().put(Long.toString(sf.getId()), sf.getName());
					}
				}
				if(fp.getId().equals("recenzent")){
					String secondTime = (String) runtimeService.getVariable(processInstanceId,"secondTime");
					if(secondTime==null){
						List<String> ids = (List<String>)runtimeService.getVariable(processInstanceId,"reviewers");
						if(ids==null){
							ids = new ArrayList<String>();
						}
						String magazineIssn = (String) runtimeService.getVariable(processInstanceId,"magazineIssn");
						Magazine m = magazineService.findOne(magazineIssn);
						List<Reviewer> magazineReviewers = reviewerService.findByMagazinesContaining(m);
						for(Reviewer r : magazineReviewers){
							User ur = r.getUser();
							if(!ids.contains(ur.getUsername())){
								String scienceFields = "";
								for(ScienceField sf : r.getFields()){
									scienceFields+=sf.getName()+",";
								}
								scienceFields=scienceFields.substring(0, scienceFields.length() - 1);
								UserDetails reviewerDetails = ur.getUserDetails();
								fp.getProperties().put(ur.getUsername(), reviewerDetails.getFirstName()+" "+reviewerDetails.getLastName()+" - "+scienceFields);
							}
						}
					}
					else{
						List<Reviewer> allReviewers = reviewerService.findAll();
						List<String> ids = (List<String>)runtimeService.getVariable(processInstanceId,"doneReviewers");
						for(Reviewer r : allReviewers){
							User ur = r.getUser();
							if(!ids.contains(ur.getUsername())){
								String scienceFields = "";
								for(ScienceField sf : r.getFields()){
									scienceFields+=sf.getName()+",";
								}								
								scienceFields=scienceFields.substring(0, scienceFields.length() - 1);
								UserDetails reviewerDetails = ur.getUserDetails();
								fp.getProperties().put(ur.getUsername(), reviewerDetails.getFirstName()+" "+reviewerDetails.getLastName()+" - "+scienceFields);
							}
						}
					}
				}
			}
		}
		return properties;
	}
	
	private HashMap<String, Object> mapListToDto(List<FormSubmissionDto> list)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(FormSubmissionDto temp : list){
			if(temp.getFieldId().equals("casopisSelect") || temp.getFieldId().equals("scientific_area") || temp.getFieldId().equals("recenzent")){
				map.put(temp.getFieldId(), null);
			}
			else{
				map.put(temp.getFieldId(), temp.getFieldValue());
			}			
		}
		
		return map;
	}
	

}
