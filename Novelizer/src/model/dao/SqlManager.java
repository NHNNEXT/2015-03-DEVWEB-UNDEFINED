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

	public void excuteUpdate(String query, DataSource ds) {
		if(ds == null){
			log.error("ds null");
		}
		try {
			Connection conn = ds.getConnection();
			if(conn == null){
				log.error("null");
			}
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			log.error("excuteUpdate " + query + "Error\n" + e);
			e.printStackTrace();
			throw new RuntimeException();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public ResultSet excuteSelect(String query, DataSource ds) {
		ResultSet resultSet = null;

		try {
			Connection conn = ds.getConnection();
			preparedStatement = conn.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			log.error("excuteSelect " + query + "Error\n" + e);
			throw new RuntimeException();
		} catch (Exception e){
			log.error("" + e);
		}
		return resultSet;
	}
}
