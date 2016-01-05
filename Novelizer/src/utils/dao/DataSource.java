package utils.dao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataSource {
	private static DataSource datasource;
	private BasicDataSource ds;

	private DataSource() throws IOException, SQLException, PropertyVetoException {
		ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUsername("root");
		ds.setPassword("novel");
		ds.setUrl("jdbc:mysql://125.209.195.85:3306/novelizer?useUnicode=true&characterEncoding=utf8");

		// the settings below are optional -- dbcp can work with defaults
		ds.setMinIdle(100);
		ds.setMaxIdle(200);
		ds.setMaxOpenPreparedStatements(1000);

	}

	public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
		if (datasource == null) {
			datasource = new DataSource();
			return datasource;
		} else {
			return datasource;
		}
	}

	public Connection getConnection() throws SQLException {
		return this.ds.getConnection();
	}

}
