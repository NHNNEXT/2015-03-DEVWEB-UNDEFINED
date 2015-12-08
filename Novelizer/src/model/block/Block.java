package model.block;

import java.util.ArrayList;
import java.util.List;

import model.ValueImpl;
import model.action.Action;

public class Block implements ValueImpl {
	private int blockId;
	private int nextBlockId;
	private int sceneId;
	private List<Action> actionList;

	public Block(int blockId, int nextBlockId) {
		super();
		this.blockId = blockId;
		this.nextBlockId = nextBlockId;
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
	public String getInsertQuery() {
		return "block(blockId, nextBlockId, sceneId) VALUES(?, ?, ?);";
	}

	@Override
	public List<Object> getInsertList() {
		ArrayList<Object> insertList = new ArrayList<Object>();
		insertList.add(this.blockId);
		insertList.add(this.nextBlockId);
		insertList.add(this.sceneId);

		return insertList;
	}

}
