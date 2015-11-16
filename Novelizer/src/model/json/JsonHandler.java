package model.json;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonHandler {
	public ArrayList<JSONObject> parseJsonToArrayList(String jsonData){
		JSONParser parser = new JSONParser();
		JSONArray blockArray = null;
		ArrayList<JSONObject> blockArrayList = new ArrayList<>();
		try {
			blockArray = (JSONArray) parser.parse(jsonData);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		for (int i = 0 ; i  < blockArray.size() ; i++){
			blockArrayList.add((JSONObject) blockArray.get(i));
		}
		
		return blockArrayList;
	}

}
