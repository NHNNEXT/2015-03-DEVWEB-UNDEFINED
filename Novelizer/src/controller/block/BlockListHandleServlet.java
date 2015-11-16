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

import model.dao.DAO;

@WebServlet("/getBlock.do")
public class BlockListHandleServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		System.out.println(getStringFromInputStream(req.getInputStream()));
	
		String headerLen = req.getHeader("Content-Length");    
		String jsonData = req.getParameter("data");
		
		System.out.println(headerLen);
		System.out.println(jsonData);
		
		BlockValidateChecker validateChecker = new BlockValidateChecker();
		if (validateChecker.isValidate(jsonData)) {
			DAO dao = new DAO();
			dao.saveBlockData(jsonData);
		}
	}
	
	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}
}
