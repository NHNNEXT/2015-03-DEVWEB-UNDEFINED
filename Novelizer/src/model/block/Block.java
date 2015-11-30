package model.block;

import java.util.List;

import model.action.Action;

public class Block {
	private int blockId;
	private int nextBlockId;
	private List<Action> actionList;

	public int getBlockId() {
		return blockId;
	}

	public int getNextBlockId() {
		return nextBlockId;
	}

	public List<Action> getActionList() {
		return actionList;
	}

	public Block(int blockId, int nextBlockId) {
		super();
		this.blockId = blockId;
		this.nextBlockId = nextBlockId;
	}

	public void setActionList(List<Action> actionList) {
		this.actionList = actionList;
	}

}
