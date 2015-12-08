package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.dao.DataSource;

public abstract class AbstractDao<V, K> implements GenericDao<V, K> {
	private static final Logger log = LoggerFactory.getLogger(AbstractDao.class);
	protected String insertQuery;
	protected String selectQuery;
	protected String selectAllQuery;
	protected String selectByParentIdQuery;
	protected String updateQuery;
	protected String deleteQuery;

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	ResultSetMetaData rsmd;

	private void init() {
		try {
			conn = DataSource.getInstance().getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert(List<Object> insertList) {
		init();

		try {
			pstmt = conn.prepareStatement(insertQuery);
			for (int i = 0; i < insertList.size(); i++) {
				pstmt.setObject(i + 1, insertList.get(i));
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		close();

	}

	public List<Object> select(K key) {
		init();

		List<Object> objects = null;

		try {
			pstmt = conn.prepareStatement(selectQuery);
			pstmt.setObject(1, key);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();

			objects = makeObjects(rsmd);

		} catch (Exception e) {
			e.printStackTrace();
		}

		close();
		return objects;
	}

	public List<List<Object>> selectAll() {
		init();
		List<List<Object>> allObjects = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(selectAllQuery);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rsmd = rs.getMetaData();
				allObjects.add(makeObjects(rsmd));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return allObjects;
	}

	public List<List<Object>> selectByParentId(K key) {
		init();
		List<List<Object>> allObjects = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(selectByParentIdQuery);
			pstmt.setObject(1, key);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rsmd = rs.getMetaData();
				allObjects.add(makeObjects(rsmd));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return allObjects;
	}

	private List<Object> makeObjects(ResultSetMetaData rsmd) throws Exception {

		List<Object> objects = new ArrayList<>();

		for (int i = 0; i < rsmd.getColumnCount(); i++) {
			objects.add(rs.getObject(i + 1));
		}

		return objects;
	}

	private void close() {
		if (rs != null)
			try {
				rs.close();
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		if (rsmd != null)
			rsmd = null;

		if (pstmt != null)
			try {
				pstmt.close();
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		if (conn != null)
			try {
				conn.close();
			} catch (Exception e) {
				log.error(e.getMessage());
			}
	}
}
