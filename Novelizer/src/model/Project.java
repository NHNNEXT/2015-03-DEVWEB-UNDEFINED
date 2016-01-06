package model;

import java.util.List;

public class Project {
	private int projectId;
	private String projectName;
	private String projectInfo;
	private String userId;
	private List<Scene> scenes;

	public Project() {

	}

	public Project(int projectId, String projectName, String projectInfo, String userId) {
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

	public String getUserId() {
		return userId;
	}

	public List<Scene> getScenes() {
		return scenes;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setScenes(List<Scene> scenes) {
		this.scenes = scenes;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + "]";
	}

}
