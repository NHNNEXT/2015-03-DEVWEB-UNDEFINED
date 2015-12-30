package model;

public class Project {
	private int projectId;
	private String projectName;
	private String projectInfo;
	private int userId;

	public Project() {

	}

	public Project(int projectId, String projectName, String projectInfo, int userId) {
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectInfo = projectInfo;
		this.userId = userId;

	}

	public int getProjectId() {
		return projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getProjectInfo() {
		return projectInfo;
	}

	public int getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + "]";
	}

}
