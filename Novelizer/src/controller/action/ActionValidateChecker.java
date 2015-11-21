package controller.action;

import com.mongodb.DBObject;

public class ActionValidateChecker {

	public boolean isValid(DBObject actionJSONData) {

		/* action의 종류와 상관없이 id와 type속성은 가져야하는데, 먼저 이것을 체크한다 */
		if (!actionPreCheck(actionJSONData))
			return false;

		String type = (String) actionJSONData.get("type");

		/* type별로 필수적으로 가져야하는 속성이 달라 분기를 구성하였다 */
		/* 설정파일에서 읽어와 분기할 수 있는 방식으로 하면 더 좋을것 같다 */
		switch (type) {
		case "BackgroundAction":
			return backgroundActionIsValid(actionJSONData);
		case "CharacterAction":
			return characterActionIsValid(actionJSONData);
		case "TextAction":
			return textActionIsValid(actionJSONData);
		}
		return false;
	}

	private boolean actionPreCheck(DBObject actionJSONData) {
		if (!actionHas(actionJSONData.get("id")))
			return false;
		if (!actionHas(actionJSONData.get("type")))
			return false;
		return true;
	}

	private boolean textActionIsValid(DBObject actionJSONData) {
		if (!actionHas(actionJSONData.get("text")))
			return false;
		if (!actionHas(actionJSONData.get("characterId")))
			return false;
		return true;
	}

	private boolean characterActionIsValid(DBObject actionJSONData) {
		if (!actionHas(actionJSONData.get("characterId")))
			return false;
		if (!actionHas(actionJSONData.get("presetId")))
			return false;
		if (!actionHas(actionJSONData.get("option")))
			return false;
		if (!actionHas(actionJSONData.get("animation")))
			return false;
		if (!actionHas(actionJSONData.get("position")))
			return false;
		return true;
	}

	private boolean backgroundActionIsValid(DBObject actionJSONData) {
		if (!actionHas(actionJSONData.get("presetId")))
			return false;
		if (!actionHas(actionJSONData.get("option")))
			return false;
		if (!actionHas(actionJSONData.get("animation")))
			return false;
		if (!actionHas(actionJSONData.get("position")))
			return false;
		return true;
	}

	private boolean actionHas(Object object) {
		if (object != null)
			return true;
		return false;
	}
}
