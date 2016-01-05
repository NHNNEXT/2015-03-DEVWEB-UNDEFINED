package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIgnoreType
public class Action {

	private int actionId;
	private String actionType;
	private int characterId;
	private int presetId;
	private int optionId;
	private float posX;
	private float posY;
	private String animation;
	private String text;

	private int blockId;

	public Action() {
	}

	public Action(int actionId, String type, int blockId) {
		this(actionId, type, blockId, null);
	}

	public Action(int actionId, String type, int blockId, String optionData) {
		this.actionId = actionId;
		this.actionType = type;
		this.blockId = blockId;
	}

	public int getActionId() {
		return actionId;
	}

	public String getActionType() {
		return actionType;
	}

	public int getOptionId() {
		return optionId;
	}

	public int getBlockId() {
		return blockId;
	}

	public int getCharacterId() {
		return characterId;
	}

	public int getPresetId() {
		return presetId;
	}

	public float getPosX() {
		return posX;
	}

	public float getPosY() {
		return posY;
	}

	public String getAnimation() {
		return animation;
	}

	public String getText() {
		return text;
	}

	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}

}
