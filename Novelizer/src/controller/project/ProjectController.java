package controller.project;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.scene.Scene;
import model.scene.SceneDao;
import project.Project;
import utils.json.JsonHandler;

@WebServlet("/project")
public class ProjectController extends HttpServlet{
	private Logger log = LoggerFactory.getLogger(ProjectController.class);
	
	private JsonHandler jsonHandler;
	private SceneDao sceneDao;
	private DataSource datasource;
	
	@Override
	public void init() throws ServletException {
		sceneDao = new SceneDao();
		datasource = (DataSource)getServletContext().getAttribute("DataSource");
		jsonHandler = new JsonHandler();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO projectId가 존재하지 않을 경우 에러가 발생한다. 이 같은경우 어떻게 처리할 것인가?
		int projectId = Integer.parseInt(req.getParameter("projectId"));
		try {
			List<Scene> scenes = sceneDao.getSceneList(projectId);
		} catch (SQLException e) {
			log.error("SQL Exception");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String projectData = req.getParameter("projectData");
		Project project = jsonHandler.convertToProject(projectData);
		resp.getWriter().write("1");
	}
	
}
