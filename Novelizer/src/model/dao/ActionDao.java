package model.dao;

import com.mongodb.Mongo;

import vo.action.Action;

public class ActionDao {

	public ActionDao() {
		Mongo conn = utils.Connection.getConnection();
		db = conn.getDB("Novelizer");
	}
	
	public void saveActionData(Action actionObj) {
				
	}

}
