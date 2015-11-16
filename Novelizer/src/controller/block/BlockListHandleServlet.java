package controller.block;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.Dao;

@WebServlet("/getBlock.do")
public class BlockListHandleServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String jsonData = req.getParameter("data");
		// BlockValidateChecker 인스턴스를 매번 생성하나? 매번 생성할 필요가 없다면 어떻게 구현하면 좋을까?
		BlockValidateChecker validateChecker = new BlockValidateChecker();
		if (validateChecker.isValidate(jsonData)) {
			// Dao 인스턴스를 매번 생성해야 하나?
			Dao dao = new Dao();
			dao.saveBlockData(jsonData);
		}
	}
}
