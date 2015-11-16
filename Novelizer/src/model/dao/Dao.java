package model.dao;

import java.util.List;

import org.json.simple.JSONObject;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

import model.json.JsonHandler;

//빈 공백 라인은 어떤 기준으로 추가했는가? 불필요한 공백 라인은 추가하지 않는다.
public class Dao {
	private DB db;

	public Dao() {
		Mongo conn;

		try {
			conn = new Mongo("127.0.0.1", 27017);
			db = conn.getDB("Novelizer");

		} catch (Exception e) {
			// exception이 발생하면 어떻게 할까? 이런 exception은 어떻게 처리하는 것이 좋을까?
			e.printStackTrace();
		}
	}

	public void saveBlockData(String jsonData) {

		DBCollection blockCollection = db.getCollection("block");
		blockCollection.drop();
		List<JSONObject> blockArrayList = new JsonHandler().parseJsonToArrayList(jsonData);
		for(JSONObject block : blockArrayList ){
			DBObject dbObject = (DBObject)JSON.parse(block.toJSONString());
			blockCollection.insert(dbObject);
		}

	}
	
	
}
