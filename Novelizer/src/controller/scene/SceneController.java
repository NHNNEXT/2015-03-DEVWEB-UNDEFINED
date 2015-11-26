package controller.scene;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/scene")
public class SceneController extends HttpServlet {
	
	private SceneService service;
	
	@Override
	public void init() throws ServletException {
		service = new SceneService();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String sceneData = req.getParameter("sceneData");
		service.saveScene(sceneData);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain;charset=UTF-8");
	}
}
