package controller.block;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.json.JsonHandler;
import vo.action.ActionType;

public class BlockValidateChecker {
	private JsonHandler mJsonHandler;

	public BlockValidateChecker() {
		mJsonHandler = new JsonHandler();
	}

	public boolean isValidate(String jsonData) {

		JSONArray blocks = mJsonHandler.convertToJSONArray(jsonData);
		if (blocks == null)
			return false;

		for (Object block : blocks) {
			if (!isBlockHasValidData((JSONObject) block)) {
				return false;
			}
		}
		return true;
	}

	private boolean isBlockHasValidData(JSONObject blockObject) {
		long blockId = (long) blockObject.get("blockId");
		long nextBlockId = (long) blockObject.get("nextBlockId");
		JSONArray actions = (JSONArray) blockObject.get("actionList");

		if (isValidateBlockId(blockId) && isValidateBlockId(nextBlockId) && isValidateActions(actions)) {
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

	private boolean isValidateActions(JSONArray actionArray) {
		for (Object action : actionArray) {
			if (!isActionHasValidData((JSONObject) action))
				return false;
		}
		return true;
	}

	private boolean isActionHasValidData(JSONObject action) {
		if (action == null) {
			System.out.println("No Action");
			return true;
		}
		if (ActionType.isActionType((String) action.get("type"))) {
			return true;
		}
		return false;
	}

}
