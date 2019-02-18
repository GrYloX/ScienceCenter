package ftn.upp.sc.dto;

public class TaskDto {
	
	String taskId;
	String name;
	
	public TaskDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TaskDto(String taskId, String name) {
		super();
		this.taskId = taskId;
		this.name = name;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
