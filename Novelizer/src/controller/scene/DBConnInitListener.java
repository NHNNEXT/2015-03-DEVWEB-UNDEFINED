package controller.scene;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class DBConnInitListener implements ServletContextListener {
	private static final Logger log = LoggerFactory.getLogger(DBConnInitListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {

		Context context = null;
		DataSource ds = null;

		try {
			context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/myconn");
		} catch (NamingException e1) {
			log.error("DataSource lookup error");
		} catch (Exception e) {
			log.error("Error of context or ds" + e);
		}

		ServletContext sc = event.getServletContext();
		sc.setAttribute("ds", ds);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		Connection conn = (Connection) sc.getAttribute("conn");
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
