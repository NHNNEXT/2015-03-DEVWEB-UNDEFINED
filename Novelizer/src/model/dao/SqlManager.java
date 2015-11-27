package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlManager {
	private static final Logger log = LoggerFactory.getLogger(SqlManager.class);

	private PreparedStatement preparedStatement;

	public void SqlManger() {
		preparedStatement = null;
	}

	public void excuteUpdate(String query) throws SQLException {
		Connection conn = null;
		try {
			conn = model.dao.DataSource.getInstance().getConnection();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			log.error("excuteUpdate " + query + "Error\n" + e);
			e.printStackTrace();
			throw new RuntimeException();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	public ResultSet excuteSelect(String query) throws SQLException {
		ResultSet resultSet = null;
		Connection conn = null;
		try {
			conn = model.dao.DataSource.getInstance().getConnection();
			preparedStatement = conn.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			log.error("excuteSelect " + query + "Error\n" + e);
			throw new RuntimeException();
		} catch (Exception e) {
			log.error("" + e);
		} 
		return resultSet;
	}
}
