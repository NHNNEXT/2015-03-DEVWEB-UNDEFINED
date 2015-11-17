package controller.block;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.BlockDao;

@WebServlet("/postBlock.do")
public class PostBlockListServlet extends HttpServlet {
	BlockValidateChecker validateChecker;

	@Override
	public void init() throws ServletException {
		validateChecker = new BlockValidateChecker();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String jsonData = req.getParameter("data");

		if (validateChecker.isValidate(jsonData)) {
			BlockDao dao = new BlockDao();
			dao.saveBlockData(jsonData);
		} else {
			resp.getWriter().write("Invalid JSON DATA");
			resp.getWriter().flush();

		}
	}

}
