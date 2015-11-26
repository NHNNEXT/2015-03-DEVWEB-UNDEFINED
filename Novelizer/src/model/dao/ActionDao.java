package model.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vo.action.Action;

public class ActionDao {
	private static final Logger log = LoggerFactory.getLogger(ActionDao.class);

	private QueryManager mQueryManager;
	private SqlManager sqlManager;

	public ActionDao() {
		mQueryManager = new QueryManager();
		sqlManager = new SqlManager();
	}

	public void newAction(Action action, int blockId) {
		String insertBlockQuery = mQueryManager.Insert("action", "acionId, type, blockId",
				action.getActionId() + "," + mQueryManager.toQueryStirng(action.getType()) + "," + blockId);
		sqlManager.excuteUpdate(insertBlockQuery);

	}

}
