package net.slipp.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.slipp.db.Database;

import java.io.IOException;
import java.sql.SQLException;



@WebServlet("/users/save")
public class SaveUserServlet extends HttpServlet {
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		User user = new User(userId, password);
		UserDao userDao = new UserDao();
		try{
			userDao.addUser(user);
		}catch(SQLException e){
			
		}

		response.sendRedirect("/");
	}

	

}
