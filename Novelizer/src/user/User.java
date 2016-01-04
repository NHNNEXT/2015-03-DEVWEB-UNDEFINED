package net.slipp.user;

import java.sql.SQLException;

import net.slipp.db.Database;

public class User {

	private String userId;
	private String password;

	public User(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;

	}

	public String getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean matchPassword(String newPassword) {
		return this.password.equals(newPassword);
	}

	public static boolean login(String userId, String password) throws UserNotFoundException, PasswordMismatchException, SQLException {
		UserDao userDao = new UserDao();
		User user = userDao.findByUserId(userId);
		if (user == null) {
			throw new UserNotFoundException();
		}
		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException();
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
