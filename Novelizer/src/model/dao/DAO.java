package model.dao;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

import model.json.JsonHandler;

public class DAO {
	private DB db;

	public DAO() {
		Mongo conn = utils.Connection.getConnection();
		db = conn.getDB("Novelizer");
	}

	public void saveBlockData(String jsonData) {
		DBCollection blockCollection = db.getCollection("block");
		blockCollection.drop();
		insertBlockData(jsonData, blockCollection);
	}

	private void insertBlockData(String jsonData, DBCollection blockCollection) {
		ArrayList<JSONObject> blockArrayList = new JsonHandler().parseJsonToArrayList(jsonData);
		for(JSONObject block : blockArrayList ){
			DBObject dbObject = (DBObject)JSON.parse(block.toJSONString());
			blockCollection.insert(dbObject);
		}
	}
}
