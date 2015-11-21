package controller.block;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getBlockList")
public class GetBlockListServlet extends HttpServlet {
	BlockController blockController;

	@Override
	public void init() throws ServletException {
		blockController = new BlockController();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sceneId = req.getParameter("sceneId");
		resp.setContentType("text/plain;charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.append(blockController.getBlockList(Integer.parseInt(sceneId)));
		writer.flush();
	}

}
