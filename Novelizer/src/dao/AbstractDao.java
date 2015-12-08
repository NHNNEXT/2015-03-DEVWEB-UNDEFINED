package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.ValueImpl;
import utils.dao.DataSource;

public abstract class AbstractDao<V extends ValueImpl, K> implements GenericDao<V, K> {
	private static final Logger log = LoggerFactory.getLogger(AbstractDao.class);

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;


	public void init() throws Exception {
		conn = DataSource.getInstance().getConnection();
	}

	public abstract void query();

	public void insert(V vo) throws Exception{
		String insertQuery = "INSERT INTO " + vo.getInsertQuery();
		pstmt = conn.prepareStatement(insertQuery);
		List<Object> insertList = vo.getInsertList();
		for(int i = 0 ; i < insertList.size() ; i++){
			pstmt.setObject(i + 1, insertList.get(i));
		}
		pstmt.executeUpdate();
		close();
		
	}

	private void close() {
		if (rs != null)
			try {
				rs.close();
			} catch (Exception e) {
				log.error(e.getMessage());
			}
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
