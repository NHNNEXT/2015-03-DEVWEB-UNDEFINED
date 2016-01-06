package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

	public Connection getConnection() {

		String url = "jdbc:mysql://125.209.195.85:3306/novelizer";
		String id = "root";
		String pw = "novel";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public void addUser(User user) throws SQLException {
		
		String sql = "insert into USERS values(?,?)";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		pstmt.setString(1, user.getUserId());
		pstmt.setString(2, user.getPassword());

		pstmt.executeUpdate();

	}

	public User findByUserId(String userId) throws SQLException {

		String sql = "select * from USERS where userId = ?";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		pstmt.setString(1, userId);

		ResultSet rs = pstmt.executeQuery();
		if (!rs.next()) {
			return null;
		}

		return new User(rs.getString("userId"), rs.getString("password"));

	}

	public void removeUser(String userId) throws SQLException {

		String sql = "delete FROM USERS WEHRE userId = ?";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		pstmt.setString(1, userId);

		pstmt.executeUpdate();
	}

}
