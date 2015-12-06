package service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.ActionDao;
import model.action.Action;
import model.block.Block;

public class ActionService {
	private static final Logger log = LoggerFactory.getLogger(SceneService.class);
	private ActionDao actionDao;
	
	public ActionService() {
		actionDao = new ActionDao();
	}

	public void saveAction(Block block) throws SQLException {
		int blockId = block.getBlockId();
		for (Action action : block.getActionList()) {
			log.info(action.toString());
			actionDao.newAction(action, blockId);
		}
	}

}
