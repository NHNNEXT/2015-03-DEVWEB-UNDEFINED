package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.ProjectService;

@WebServlet("/newproject")
public class ProjectsController extends HttpServlet {
	private Logger log = LoggerFactory.getLogger(ProjectsController.class);

	private ProjectService service;

	@Override
	public void init() throws ServletException {
		service = new ProjectService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = getUserId(req);
		//resp.getWriter().write(service.getProjectsByUserId(userId));

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String projectData = req.getParameter("projectData");
		log.info(projectData);
		String userId = getUserId(req);
		log.info(userId);
		String result = "";
		try {
			result += service.saveProject(projectData, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.getWriter().write(result);
	}

	private String getUserId(HttpServletRequest req) {
		HttpSession session = req.getSession();
		return "" + session.getAttribute("userId");
	}
}
