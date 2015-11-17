package model.dao;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

import model.json.JsonHandler;

public class BlockDao {
	private DB db;
	private JsonHandler jsonHandler;

	public BlockDao() {
		Mongo conn = utils.Connection.getConnection();
		db = conn.getDB("Novelizer");
		jsonHandler = new JsonHandler();
	}

	public void saveBlockData(String jsonData) {
		DBCollection blockCollection = db.getCollection("block");
		blockCollection.drop();
		insertBlockData(jsonData, blockCollection);
		// System.out.println("insertBlockData Complete");
	}

	private void insertBlockData(String jsonData, DBCollection blockCollection) {
		ArrayList<JSONObject> blockList = jsonHandler.parseToBlockList(jsonHandler.convertToJSONArray(jsonData));
		for (JSONObject block : blockList) {
			DBObject dbObject = (DBObject) JSON.parse(block.toJSONString());
			blockCollection.insert(dbObject);
		}
	}

	public String getBlockList() {
		DBCollection blockCollection = db.getCollection("block");
		DBCursor cursor = blockCollection.find();
		String blockListJson = "[";
		while (cursor.hasNext()) {
			blockListJson += cursor.next().toString();
			blockListJson += ",";
		}
		blockListJson = blockListJson.substring(0, blockListJson.length() - 1);
		blockListJson += "]";

		return blockListJson;
	}
}
