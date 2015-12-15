package model;

public class Project {
	private int projectId;
	private String projectName;
	private int userId;

	public Project(){
		
	}
	
	public Project(int projectId, String projectName , int userId) {
		this.projectId = projectId;
		this.projectName = projectName;
		this.userId = userId;
	}

	public int getProjectId() {
		return projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public int getUserId(){
		return userId;
	}
	
	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + "]";
	}

	
	
}
