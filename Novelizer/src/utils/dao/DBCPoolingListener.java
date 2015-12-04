package utils.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class DBCPoolingListener implements ServletContextListener{
	private static final Logger logger = LoggerFactory.getLogger(DBCPoolingListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			Context init = (Context) new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/novelizerDB");
			sce.getServletContext().setAttribute("DataSource", ds);
			logger.info("Connection success");
		} catch (NamingException e) {
			e.printStackTrace();
			logger.info("Connection failed");
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		sce.getServletContext().removeAttribute("DataSource");
	}
}
