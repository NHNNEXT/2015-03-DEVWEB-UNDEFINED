package controller.block;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.json.JsonHandler;

public class BlockValidateChecker {
	private JsonHandler mJsonHandler;

	public BlockValidateChecker() {
		mJsonHandler = new JsonHandler();
	}

	public boolean isValidate(String jsonData) {

		JSONArray blockListArray = mJsonHandler.convertToJSONArray(jsonData);
		if (blockListArray == null)
			return false;

		for (int i = 0; i < blockListArray.size(); i++) {
			JSONObject blockObject = (JSONObject) blockListArray.get(i);
			if (!isBlockHasEssentialData(blockObject)) {
				return false;
			}
		}
		return true;
	}

	private boolean isBlockHasEssentialData(JSONObject blockObject) {
		long blockId = (long) blockObject.get("blockId");
		long nextBlockId = (long) blockObject.get("nextBlockId");
		JSONArray actionArray = (JSONArray) blockObject.get("actionList");

		if (isValidateBlockId(blockId) && isValidateBlockId(nextBlockId) && isValidateActionList(actionArray)) {
			return true;
		} else {
			System.out.println("Block Data Error");
			return false;
		}

	}

	private boolean isValidateBlockId(long blockId) {
		if (blockId >= 0) {
			return true;
		} else {
			System.out.println("Block Id is invalidate");
			return false;
		}
	}

	private boolean isValidateActionList(JSONArray actionArray) {
		for (int i = 0; i < actionArray.size(); i++) {
			JSONObject actionObject = (JSONObject) actionArray.get(i);
			if (!isActionHasEssentialData(actionObject))
				return false;
		}
		return true;
	}

	private boolean isActionHasEssentialData(JSONObject actionObject) {
		if (actionObject == null) {
			System.out.println("No Action");
			return true;
		}
		String actionType = (String) actionObject.get("type");

		if (isValidateActionType(actionType)) {
			return true;
		}
		return false;
	}

	private boolean isValidateActionType(String actionType) {
		if (actionType.equals("text") || actionType.equals("character") || actionType.equals("background")) {
			return true;
		} else {
			System.out.println("ActionType is invalidate");
			return false;
		}
	}

}
