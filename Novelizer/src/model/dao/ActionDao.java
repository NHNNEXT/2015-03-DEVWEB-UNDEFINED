package model.dao;

import org.json.simple.JSONObject;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class ActionDao {
	private DB db;

	public ActionDao() {
		Mongo conn = utils.Connection.getConnection();
		db = conn.getDB("Novelizer");
	}

	public void saveActionData(DBObject actionJSONData) {
		DBCollection actionCollection = db.getCollection("action");
		actionCollection.drop();
		insertActionData(actionCollection, actionJSONData);
	}

	private void insertActionData(DBCollection actionCollection, DBObject actionJSONData) {
		actionCollection.insert(actionJSONData);
	}

}
