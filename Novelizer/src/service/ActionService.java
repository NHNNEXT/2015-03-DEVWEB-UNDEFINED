package service;

import java.sql.SQLException;

import dao.ActionDao;
import model.Action;
import model.Block;

public class ActionService {
	private ActionDao actionDao;

	public ActionService() {
		actionDao = new ActionDao();
	}

	public void saveAction(Block block) throws SQLException {
		int blockId = block.getBlockId();
		for (Action action : block.getActionList()) {
			action.setBlockId(blockId);
			actionDao.insertAction(action);
		}
	}

}
