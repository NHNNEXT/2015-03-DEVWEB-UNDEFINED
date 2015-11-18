package model.dao;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

import vo.action.Action;

public class ActionDao {
	private DB db;

	public ActionDao() {
		Mongo conn = utils.Connection.getConnection();
		db = conn.getDB("Novelizer");
	}
	
	public void saveActionData(Action actionObj) {
		DBCollection actionCollection = db.getCollection("action");
		actionCollection.drop();
		insertActionData(actionCollection, actionObj);
	}

	private void insertActionData(DBCollection actionCollection, Action actionObj) {
				
	}

}
