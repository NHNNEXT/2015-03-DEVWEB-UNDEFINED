package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.Connector;

public class SqlManager {
	private static final Logger log = LoggerFactory.getLogger(SqlManager.class);

	private Connector connector;

	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public void SqlManger() {
		connector = new Connector();
		preparedStatement = null;
		resultSet = null;
	}

	public void excuteUpdate(String query) {
		Connection conn = connector.getConnection();
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			log.error("excuteUpdate " + query + "Error\n" + e);
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public ResultSet excuteSelect(String query) {
		ResultSet resultSet;
		Connection conn = connector.getConnection();
		try {
			preparedStatement = conn.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			log.error("excuteSelect " + query + "Error\n" + e);
			throw new RuntimeException();
		}
		return resultSet;
	}
}
