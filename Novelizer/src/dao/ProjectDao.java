package dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Project;

public class ProjectDao extends AbstractDao<Project> {
	private static final Logger log = LoggerFactory.getLogger(ProjectDao.class);

	public ProjectDao() {
		super.insertQuery = insertQuery;
		super.selectQuery = selectQuery;
		super.selectAllQuery = selectAllQuery;
	}

	public int insertProject(Project project) {
		super.insertQuery = "INSERT INTO project(project_id, project_name, project_info, user_id) values(?, ?, ?, ?);";
		
		try {
			ArrayList<Object> insertList = new ArrayList<Object>();
			insertList.add(project.getProjectId());
			insertList.add(project.getProjectName());
			insertList.add(project.getProjectInfo());
			insertList.add(project.getUserId());
	
			return insert(insertList);
		} catch (Exception e) {
			log.error("Project Insert Error");
			e.printStackTrace();
		}
		return -1;
	}

	public List<Project> selectAllProject() {
		super.selectAllQuery =  "SELECT * FROM project;";
		
		List<Project> projects = new ArrayList<Project>();
		for (List<Object> project : selectAll()) {
			projects.add(convertToProject(project));
		}
		
		return projects;

	}
	
	public List<Project> selectProjectsByUserId(String userId) {
		super.selectByParentIdQuery = "SELECT * FROM project WHERE user_id = ?";
		
		List<Project> projects = new ArrayList<Project>();
		for (List<Object> project : selectByParentId(userId)) {
			projects.add(convertToProject(project));
		}
		
		return projects;
	}

	public Project selectProject(int projectId) {
		super.selectQuery = "SELECT * FROM project WHERE project_id = ?;";
		
		List<Object> projectContents = new ArrayList<>();
		Project project = null;

		try {
			projectContents = select(projectId);
			project = convertToProject(projectContents);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return project;
	}

	private Project convertToProject(List<Object> projectContents) {
		int projectId = (int) projectContents.get(0);
		String projectName = (String) projectContents.get(1);
		String projectInfo = (String) projectContents.get(2);
		String userId = (String) projectContents.get(3);
		return new Project(projectId, projectName, projectInfo, userId);
	}

	@Override
	public void update(Project vo) throws Exception {

	}

	@Override
	public void delete(int key) throws Exception {

	}
	
}
