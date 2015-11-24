package controller.block;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.BlockDao;

@WebServlet("/blockList")
public class BlockListController extends HttpServlet {
	BlockService blockService;

	@Override
	public void init() throws ServletException {
		blockService = new BlockService();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String jsonData = req.getParameter("data");

		if (blockService.isValidateBlockList(jsonData)) {
			BlockDao dao = new BlockDao();
			dao.saveBlockData(jsonData);
		} else {
			resp.getWriter().write("Error : Invalid JSON DATA");
			resp.getWriter().flush();

		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sceneId = req.getParameter("sceneId");
		resp.setContentType("text/plain;charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.append(blockService.getBlockList(Integer.parseInt(sceneId)));
		writer.flush();
	}

}