package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.SceneService;

@WebServlet("/scene")
public class SceneController extends HttpServlet {
	private Logger log = LoggerFactory.getLogger(SceneController.class);

	private SceneService service;

	@Override
	public void init() throws ServletException {
		service = new SceneService();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sceneData = req.getParameter("sceneData");
		log.info(sceneData);
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
		int sceneId = Integer.parseInt(req.getParameter("sceneId"));
		log.info("new Get");
		String result;
		try {
			result = service.getScene(sceneId);
		} catch (Exception e) {
			result = "error : " + e;
			e.printStackTrace();
		}
		log.info(result);
		resp.setStatus(200);
		resp.getWriter().print(result);
	}
}
