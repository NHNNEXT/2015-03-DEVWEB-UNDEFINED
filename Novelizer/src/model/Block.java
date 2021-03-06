package model;

import java.util.List;

public class Block {
	private int blockId;
	private List<Action> actionList;
	private int nextBlockId;
	private int sceneId;
	
	public Block(){}

	public Block(int blockId, int nextBlockId, int sceneId) {
		super();
		this.blockId = blockId;
		this.nextBlockId = nextBlockId;
		this.sceneId = sceneId;
	}

	public int getBlockId() {
		return blockId;
	}

	public int getNextBlockId() {
		return nextBlockId;
	}

	public int getSceneId() {
		return sceneId;
	}

	public List<Action> getActionList() {
		return actionList;
	}

	public void setActionList(List<Action> actionList) {
		this.actionList = actionList;
	}

	public void setSceneId(int sceneId) {
		this.sceneId = sceneId;
	}

	@Override
	public String toString() {
		return "Block [blockId=" + blockId + ", actionList=" + actionList + ", nextBlockId=" + nextBlockId
				+ ", sceneId=" + sceneId + "]";
	}

	
}
