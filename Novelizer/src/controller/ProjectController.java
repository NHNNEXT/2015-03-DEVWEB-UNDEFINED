package controller;

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

import dao.ProjectDao;
import model.Project;
import service.ProjectService;

@WebServlet("/project")
public class ProjectController extends HttpServlet {
	private Logger log = LoggerFactory.getLogger(ProjectController.class);

	private ProjectService service;

	@Override
	public void init() throws ServletException {
		service = new ProjectService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userId = Integer.parseInt(req.getParameter("userId"));
		resp.getWriter().write(service.getProjects());

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String projectData = req.getParameter("projectData");
		String result = "";
		try {
			log.info(projectData);
			result += service.saveProject(projectData);
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.getWriter().write(result);
	}

}
