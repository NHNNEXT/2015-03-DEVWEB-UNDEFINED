package utils;

import com.mongodb.Mongo;

public class Connection {
	public static Mongo getConnection() {
		try {
			return new Mongo("127.0.0.1", 27017);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
