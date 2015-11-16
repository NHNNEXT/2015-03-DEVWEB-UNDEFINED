package controller.block;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// 불필요한 공백 라인을 추가하지 않는다.
public class BlockValidateChecker {
	public boolean isValidate(String jsonData){
		
		JSONParser parser = new JSONParser();
		
		// 현재 indent가 3이다. indent를 더 작은 단위로 줄여본다.
		try {
			JSONArray blockListArray = (JSONArray)parser.parse(jsonData);
			for(int i = 0 ; i < blockListArray.size() ; i++){
				JSONObject blockObject = (JSONObject)blockListArray.get(i);
				System.out.println(blockObject);
				if(!isBlockHasEssentialData(blockObject)){ return false; }
			}
			
		} catch (ParseException e) {
			// exception이 발생하는 경우에도 true를 반환하는 것이 맞나?
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
		// 중괄호({}) convention을 통일해서 사용한다.
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
		// || 좌우에 space를 두지 않는 것이 convention인가? 위 코드를 보면 &&는 좌우에 공백을 두고 있다.
		// 이 부분 로직의 java의 enum을 활용해 해결해 본다.
		if(actionType.equals("text")||actionType.equals("character")||actionType.equals("background")){
			return true;
		}else{
			System.out.println("ActionType is invalidate");
			return false;
		}
	}
	
}
