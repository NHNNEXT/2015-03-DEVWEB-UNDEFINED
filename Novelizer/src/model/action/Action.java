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

<<<<<<< HEAD
	// TODO 이 두 개의 생성자를 하나로 합칠 수는 없을까? 가변 인자를 사용한다면...
	public Action(int actionId, String type) {
	
		this(actionId, type, null);
	}

	
	public Action(int actionId, String type, String optionData) {
=======
	public Action(int actionId, String type , int blockId) {
		this(actionId, type, blockId, null);
	}

	public Action(int actionId, String type, int blockId, String optionData) {
>>>>>>> f59a1a0b752e4c218d85a2d5760c94eac2d3f97e
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
