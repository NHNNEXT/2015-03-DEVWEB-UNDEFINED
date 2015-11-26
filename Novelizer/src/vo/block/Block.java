package vo.block;

import java.util.List;
import vo.action.Action;

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

}
