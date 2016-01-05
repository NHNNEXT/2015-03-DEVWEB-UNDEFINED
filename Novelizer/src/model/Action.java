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
		this(actionId, type, 0, 0, 0, 0.0f, 0.0f, null, null, blockId);
	}

	public Action(int actionId, String actionType, int characterId, int presetId, int optionId, float posX, float posY,
			String animation, String text, int blockId) {
		super();
		this.actionId = actionId;
		this.actionType = actionType;
		this.characterId = characterId;
		this.presetId = presetId;
		this.optionId = optionId;
		this.posX = posX;
		this.posY = posY;
		this.animation = animation;
		this.text = text;
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

	@Override
	public String toString() {
		return "Action [actionId=" + actionId + ", actionType=" + actionType + ", characterId=" + characterId
				+ ", presetId=" + presetId + ", optionId=" + optionId + ", posX=" + posX + ", posY=" + posY
				+ ", animation=" + animation + ", text=" + text + ", blockId=" + blockId + "]";
	}

}
