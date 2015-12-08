package model.action;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import model.option.Option;
import utils.json.JsonHandler;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Action {

	private int actionId;
	private String type;
	private Option optionData;
	private int blockId;

	public Action() {
	}

	public Action(int actionId, String type , int blockId) {
		this(actionId, type, blockId, null);
	}

	public Action(int actionId, String type, int blockId, String optionData) {
		this.actionId = actionId;
		this.type = type;
		this.blockId = blockId;

		if (optionData != null) {
			this.optionData = new JsonHandler().convertToOption(optionData);
		}
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

	public int getBlockId() {
		return blockId;
	}

	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}

	@Override
	public String toString() {
		return "Action [actionId=" + actionId + ", type=" + type + ", optionData=" + optionData + "]";
	}

}
