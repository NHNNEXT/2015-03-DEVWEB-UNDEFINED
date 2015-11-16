package controller.block;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.Dao;

@WebServlet("/postBlock.do")
public class PostBlockListServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String jsonData = req.getParameter("data");
		
		System.out.println(jsonData);
		
		BlockValidateChecker validateChecker = new BlockValidateChecker();
		if (validateChecker.isValidate(jsonData)) {
			Dao dao = new Dao();
			dao.saveBlockData(jsonData);
		}
	}
	

}
