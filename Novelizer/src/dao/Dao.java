package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class Dao {
	private DB db;

	public Dao() {
		Mongo conn;

		try {
			conn = new Mongo("127.0.0.1", 27017);
			db = conn.getDB("Novelizer");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveBlockData(String jsonData) {

		DBCollection blockCollection = db.getCollection("block");
		DBObject dbObject = new BasicDBObject();
		((BasicDBObject) dbObject).append("json",jsonData);
		blockCollection.insert(dbObject);
	}

}
