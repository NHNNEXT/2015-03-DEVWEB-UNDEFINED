package model.action;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import model.ValueImpl;
import model.option.Option;
import utils.json.JsonHandler;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Action implements ValueImpl{

	private int actionId;
	private String type;
	private Option optionData;

	public Action() {
	}

	public Action(int actionId, String type) {
		this(actionId, type, null);
	}

	public Action(int actionId, String type, String optionData) {
		this.actionId = actionId;
		this.type = type;

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

	@Override
	public String toString() {
		return "Action [actionId=" + actionId + ", type=" + type + ", optionData=" + optionData + "]";
	}

	@Override
	public String getInsertQuery() {
		return "(actionId, type, blockId) values(?,?,?);";
	}

	@Override
	public List<Object> getInsertList() {
		ArrayList<Object> insertList = new ArrayList<Object>();
		insertList.add(actionId);
		insertList.add(type);
		insertList.add(1);
		return insertList;
	}

}
