package service;

import dao.ProjectDao;
import model.Project;
import utils.json.JsonHandler;

public class ProjectService {
	private JsonHandler<Project> jsonHandler;
	private ProjectDao projectDao;

	public ProjectService() {
		jsonHandler = new JsonHandler<Project>();
		projectDao = new ProjectDao();
	}

	public String saveProject(String projectData) {
		Project project = jsonHandler.convertToProject(projectData);
		return jsonHandler.convertToJson(projectDao.insertProject(project));
	}

}
