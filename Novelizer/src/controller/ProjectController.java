package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProjectService;

@WebServlet("/project")
public class ProjectController extends HttpServlet{
	
	private ProjectService service;

	@Override
	public void init() throws ServletException {
		service = new ProjectService();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
		String projectId = "" + req.getAttribute("projectId");
		try {
			resp.getWriter().print(service.getProject(Integer.parseInt(projectId)));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
