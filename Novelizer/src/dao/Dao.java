package dao;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

public class Dao {
	private DB db;
	private DBCollection blockCollection;
	
	public Dao(){
		
		Mongo conn;
		
		try{
			conn = new Mongo("127.0.0.1", 27017);
			db = conn.getDB("Novelizer");
			blockCollection = db.getCollection("block");
			
		}catch(Exception e){
			 e.printStackTrace();
		}
	}
	
	public void saveData(String jsonData){
		DBObject dbObject = (DBObject)JSON.parse(jsonData);
		
		blockCollection.insert(dbObject);
	}
	
}
