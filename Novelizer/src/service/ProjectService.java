package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.ProjectDao;
import model.Project;
import utils.json.JsonHandler;

public class ProjectService {
	private static final Logger log = LoggerFactory.getLogger(ProjectService.class);
	
	private JsonHandler<Project> jsonHandler;
	private ProjectDao projectDao;

	public ProjectService() {
		jsonHandler = new JsonHandler<Project>();
		projectDao = new ProjectDao();
	}

	public int saveProject(String projectData) {
		Project project = jsonHandler.convertToProject(projectData);
		return projectDao.insertProject(project);
	}

}
