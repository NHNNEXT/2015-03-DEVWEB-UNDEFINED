package dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Project;

public class ProjectDao extends AbstractDao<Project> {
	private static final Logger log = LoggerFactory.getLogger(ProjectDao.class);

	private final String insertQuery = "INSERT INTO project(name) values(?);";
	private final String selectQuery = "SELECT * FROM project WHERE projectId = ?;";
	private final String selectAllQuery = "SELECT * FROM project;";

	public ProjectDao() {
		super.insertQuery = insertQuery;
		super.selectQuery = selectQuery;
		super.selectAllQuery = selectAllQuery;
	}

	public int insertProject(Project project) {
		try {
			ArrayList<Object> insertList = new ArrayList<Object>();
			insertList.add(project.getProjectName());
			return insert(insertList);
		} catch (Exception e) {
			log.error("Project Insert Error");
			e.printStackTrace();
		}
		return -1;
	}

	public List<Project> selectAllProject() {
		List<Project> projects = new ArrayList<Project>();
		for (List<Object> project : selectAll()) {
			projects.add(convertToProject(project));
		}
		
		return projects;

	}

	public Project selectProject(int sceneId) {

		List<Object> projectContents = new ArrayList<>();
		Project project = null;

		try {
			projectContents = select(sceneId);
			project = convertToProject(projectContents);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return project;
	}

	private Project convertToProject(List<Object> projectContents) {
		int projectId = (int) projectContents.get(0);
		String projectName = (String) projectContents.get(1);
		return new Project(projectId, projectName);
	}

	@Override
	public void update(Project vo) throws Exception {

	}

	@Override
	public void delete(int key) throws Exception {

	}
}
