package model;

import java.util.List;

public class Block {
	private int blockId;
	private int nextBlockId;
	private int sceneId;
	private List<Action> actionList;

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

}
