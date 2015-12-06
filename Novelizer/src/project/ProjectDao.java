package project;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.dao.QueryManager;
import utils.dao.SqlManager;

public class ProjectDao {

	private static final Logger log = LoggerFactory.getLogger(ProjectDao.class);
		
	private QueryManager mQueryManager;
	private SqlManager sqlManager;

	public ProjectDao() {
		mQueryManager = new QueryManager();
		sqlManager = new SqlManager();
	}

	public void newProject(Project project) throws SQLException {
		
	}
}
