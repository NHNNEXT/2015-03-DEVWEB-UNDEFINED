package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {
	public void excuteUpdate(String sql, PreparedStatementSetter pss, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pss.setParameters(pstmt);
			pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public Object executeQuery(String sql, PreparedStatementSetter pss, RowMapper rm, Connection conn) throws SQLException {
 		PreparedStatement pstmt = null;
		ResultSet rs = null;
 		try {
 			pstmt = conn.prepareStatement(sql); 
			pss.setParameters(pstmt);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			return rm.mapRow(rs);
 		} finally {
			if (rs != null) {
				rs.close();
			}
			
 			if (pstmt != null) {
 				pstmt.close();
 			}
 		}
	}
}
