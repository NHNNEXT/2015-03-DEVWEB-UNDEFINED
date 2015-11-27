package vo.action;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Action {

	private int actionId;
	private String type;
	private Option optionData;

	public Action(){}
	
	public Action(int actionId, String type){
		this.actionId = actionId;
		this.type = type;
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
