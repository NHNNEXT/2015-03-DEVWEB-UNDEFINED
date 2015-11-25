package utils;

import java.sql.SQLException;
import java.sql.PreparedStatement;

public interface PreparedStatementSetter {
	void setParameters(PreparedStatement pstmt) throws SQLException;
}
