package model.dao;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

import model.json.JsonHandler;

public class Dao {
	private DB db;

	public Dao() {
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
	
	public String getBlockList(){
		DBCollection blockCollection = db.getCollection("block");
		DBCursor cursor = blockCollection.find();
		String blockListJson = "[";
		while(cursor.hasNext()){
			blockListJson += cursor.next().toString();
			blockListJson += ",";
		}
		blockListJson = blockListJson.substring(0,blockListJson.length() - 1);
		blockListJson += "]";
		
		return blockListJson;
	}
}
