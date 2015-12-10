package model;

public class Project {
	private int projectId;
	private String projectName;

	public Project(){
		
	}
	
	public Project(int projectId, String projectName) {
		this.projectId = projectId;
		this.projectName = projectName;
	}

	public int getProjectId() {
		return projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + "]";
	}

	
	
}
