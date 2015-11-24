package model.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonHandler {
	JSONParser mParser;

	public JsonHandler() {
		mParser = new JSONParser();
	}

	public JSONObject convertToJSONObject(String jsonData) {
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) mParser.parse(jsonData);
		} catch (ParseException e) {
			System.out.println("JSON Convert FAIL!!");
		}
		return jsonObject;
	}

	public JSONArray convertToJSONArray(String jsonData) {
		JSONArray jsonArray = null;
		try {
			jsonArray = (JSONArray) mParser.parse(jsonData);
		} catch (ParseException e) {
			System.out.println("JSON Convert FAIL!!");
		}
		return jsonArray;
	}

}
