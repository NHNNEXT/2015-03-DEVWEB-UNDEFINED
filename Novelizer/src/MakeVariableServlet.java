import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/makeVariable")
public class MakeVariableServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String variable = req.getParameter("variable");
		insertVariableOnDB(variable);

	}

	// DB에 variable 업로드 하는 코드 작성 필요
	private void insertVariableOnDB(String variable) {

	}
}
