package controller.block;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BlockValidateChecker {
	public boolean isValidate(String jsonData){
		
		JSONParser parser = new JSONParser();
		
		try {
			JSONArray blockListArray = (JSONArray)parser.parse(jsonData);
			for(int i = 0 ; i < blockListArray.size() ; i++){
				JSONObject blockObject = (JSONObject)blockListArray.get(i);
				System.out.println(blockObject);
				if(!isBlockHasEssentialData(blockObject)){ return false; }
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	private boolean isBlockHasEssentialData(JSONObject blockObject){
		long blockId = (long)blockObject.get("blockId");
		long nextBlockId = (long)blockObject.get("nextBlockId");
		JSONArray actionArray = (JSONArray)blockObject.get("actionList");
		
		if(isValidateBlockId(blockId) && isValidateBlockId(nextBlockId) && isValidateActionList(actionArray)){
			return true;
		}else{
			System.out.println("Block Data Error");
			return false;
		}
			
		
	}
	
	private boolean isValidateBlockId(long blockId){
		if(blockId >= 0){
			return true;
		}
		else{
			System.out.println("Block Id is invalidate");
			return false;
		}
	}
	
	private boolean isValidateActionList(JSONArray actionArray){
		for(int i = 0 ; i < actionArray.size() ;i++){
			JSONObject actionObject = (JSONObject) actionArray.get(i);
			if(!isActionHasEssentialData(actionObject)) return false;
		}
		return true;
	}
	
	private boolean isActionHasEssentialData(JSONObject actionObject){
		String actionType = (String) actionObject.get("type");
		
		if(isValidateActionType(actionType)){
			return true;
		}
		return false;
	}
	
	private boolean isValidateActionType(String actionType){
		if(actionType.equals("text")||actionType.equals("character")||actionType.equals("background")){
			return true;
		}else{
			System.out.println("ActionType is invalidate");
			return false;
		}
	}
	
}
