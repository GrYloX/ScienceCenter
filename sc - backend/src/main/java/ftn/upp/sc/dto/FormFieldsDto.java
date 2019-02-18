package ftn.upp.sc.dto;

import java.util.List;

import org.camunda.bpm.engine.form.FormField;

public class FormFieldsDto {
	String taskId;
	List<FormField> formFields;
	String processInstanceId;
	String taskName;

	public FormFieldsDto(String taskId, String processInstanceId, List<FormField> formFields, String taskName) {
		super();
		this.taskId = taskId;
		this.formFields = formFields;
		this.processInstanceId = processInstanceId;
		this.taskName = taskName;
	}

	public FormFieldsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public List<FormField> getFormFields() {
		return formFields;
	}

	public void setFormFields(List<FormField> formFields) {
		this.formFields = formFields;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	
}
