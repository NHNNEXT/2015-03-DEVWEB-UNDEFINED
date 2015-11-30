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

	public Action(int actionId, String type) {
		this.actionId = actionId;
		this.type = type;
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
