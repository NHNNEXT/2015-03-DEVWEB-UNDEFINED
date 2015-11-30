package controller.project;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.scene.Scene;
import model.scene.SceneDao;

@WebServlet("/project")
public class ProjectController extends HttpServlet{
	private Logger log = LoggerFactory.getLogger(ProjectController.class);
	private SceneDao sceneDao;
	
	@Override
	public void init() throws ServletException {
		sceneDao = new SceneDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int projectId = Integer.parseInt(req.getParameter("projectId"));
		try {
			List<Scene> scenes = sceneDao.getSceneList(projectId);
		} catch (SQLException e) {
			log.error("SQL Exception");
			e.printStackTrace();
		}
	}
	
}
