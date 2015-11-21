package model.json;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonHandler {
	JSONParser mParser;

	public JsonHandler() {
		mParser = new JSONParser();
	}

	public JSONArray convertToJSONArray(String jsonData) {
		JSONArray blockArray = null;
		try {
			blockArray = (JSONArray) mParser.parse(jsonData);
		} catch (ParseException e) {
			System.out.println("JSON Convert FAIL!!");
		}
		return blockArray;
	}

	public ArrayList<JSONObject> parseToBlockList(JSONArray blockArray) {
		ArrayList<JSONObject> blockArrayList = new ArrayList<JSONObject>();
		for (int i = 0; i < blockArray.size(); i++) {
			blockArrayList.add((JSONObject) blockArray.get(i));
		}
		return blockArrayList;
	}

}
