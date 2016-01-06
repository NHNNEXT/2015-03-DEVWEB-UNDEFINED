package service;

import java.util.List;

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

	public String getProjects() {
		List<Project> projects = projectDao.selectAllProject();
		log.info(jsonHandler.convertToJsonArray(projects));
		return jsonHandler.convertToJsonArray(projects);

	}
	
	public String getProjectsByUserId(String userId) {
		List<Project> projects = projectDao.selectProjectsByUserId(userId);
		return jsonHandler.convertToJsonArray(projects);
	}

	public int saveProject(String projectData, String userId) {
		Project project = jsonHandler.convertToProject(projectData);
		project.setUserId(userId);
		log.info(project.toString());
		return projectDao.insertProject(project);
	}

}
