package service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.ProjectDao;
import dao.SceneDao;
import model.Project;
import utils.json.JsonHandler;

public class ProjectService {
	private static final Logger log = LoggerFactory.getLogger(ProjectService.class);

	private JsonHandler<Project> jsonHandler;
	private ProjectDao projectDao;
	private SceneDao sceneDao;

	public ProjectService() {
		jsonHandler = new JsonHandler<Project>();
		projectDao = new ProjectDao();
		sceneDao = new SceneDao();
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
	
	public String getProject(int projectId) throws SQLException{
		Project project = projectDao.selectProject(projectId);
		project.setScenes(sceneDao.selectByProjectId(projectId));
		return jsonHandler.convertToJson(project);
	}

	public int saveProject(String projectData, String userId) {
		Project project = jsonHandler.convertToProject(projectData);
		project.setUserId(userId);
		log.info(project.toString());
		return projectDao.insertProject(project);
	}

}
