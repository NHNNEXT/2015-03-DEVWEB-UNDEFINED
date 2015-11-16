package model.json;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonHandler {
	// ParseException이 발생하면 무슨 일이 벌어질까?
	public List<JSONObject> parseJsonToArrayList(String jsonData){
		JSONParser parser = new JSONParser();
		JSONArray blockArray = null;
		// ArrayList<JSONObject> blockArrayList = new ArrayList<>();
		List<JSONObject> blocks = new ArrayList<>();
		// ArrayList를 사용할 때와 List를 사용할 때 어느 것이 더 좋을까? 판단 기준은 무엇인가?
		try {
			blockArray = (JSONArray) parser.parse(jsonData);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// for(Object block; blockArray) {}를 사용해 구현해 본다.
		for (int i = 0 ; i  < blockArray.size() ; i++){
			blocks.add((JSONObject) blockArray.get(i));
		}
		
		return blocks;
	}

}
