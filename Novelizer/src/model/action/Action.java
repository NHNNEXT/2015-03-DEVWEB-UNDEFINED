package model.action;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import model.option.Option;
import utils.json.JsonHandler;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Action {

	private int actionId;
	private String type;
	private Option optionData;

	public Action() {
	}

	// TODO 이 두 개의 생성자를 하나로 합칠 수는 없을까? 가변 인자를 사용한다면...
	public Action(int actionId, String type) {
	
		this(actionId, type, null);
	}

	
	public Action(int actionId, String type, String optionData) {
		this.actionId = actionId;
		this.type = type;
		this.optionData = new JsonHandler().convertToOption(optionData);
	}

	public int getActionId() {
		return actionId;
	}

	public String getType() {
		return type;
	}

	public Option getOptionData() {
		return optionData;
	}

	@Override
	public String toString() {
		return "Action [actionId=" + actionId + ", type=" + type + ", optionData=" + optionData + "]";
	}

}
