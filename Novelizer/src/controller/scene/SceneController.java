package controller.scene;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/scene")
public class SceneController extends HttpServlet {

	private SceneService service;

	@Override
	public void init() throws ServletException {
		service = new SceneService();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sceneData = req.getParameter("sceneData");
		String result;
		try {
			result = service.saveScene(sceneData);
		} catch (Exception e) {
			result = "error : " + e;
		}
		resp.getWriter().print(result);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sceneId = req.getParameter("sceneId");
		String result;
		try {
			result = service.getScene(sceneId);
		} catch (Exception e) {
			result = "error : " + e;
			e.printStackTrace();
		}

		resp.getWriter().print(result);
	}
}
