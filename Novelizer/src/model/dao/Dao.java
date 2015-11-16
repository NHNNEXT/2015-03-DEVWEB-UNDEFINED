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
		blockCollection.drop();
		ArrayList<JSONObject> blockArrayList = new JsonHandler().parseJsonToArrayList(jsonData);
		for(JSONObject block : blockArrayList ){
			DBObject dbObject = (DBObject)JSON.parse(block.toJSONString());
			blockCollection.insert(dbObject);
		}

	}
	
	
}
