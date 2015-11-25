package controller.scene;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import utils.Connector;

@WebListener
public class DBConnInitListener implements ServletContextListener{
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		Connection conn = new Connector().getConnection();
		sc.setAttribute("conn", conn);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		Connection conn = (Connection)sc.getAttribute("conn");
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
