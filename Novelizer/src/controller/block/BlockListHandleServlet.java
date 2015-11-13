package controller.block;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;

@WebServlet("/getBlock.do")
public class BlockListHandleServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//String jsonData = ;
		String jsonData = req.getParameter("data");
		BlockValidateChecker validateChecker = new BlockValidateChecker();
		if (validateChecker.isValidate(jsonData)) {
			Dao dao = new Dao();
			dao.saveBlockData(jsonData);
		}
	}
}
