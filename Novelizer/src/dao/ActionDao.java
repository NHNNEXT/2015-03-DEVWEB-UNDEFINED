package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.action.Action;
import utils.dao.QueryManager;
import utils.dao.SqlManager;

public class ActionDao {

	private QueryManager mQueryManager;
	private SqlManager sqlManager;

	public ActionDao() {
		mQueryManager = new QueryManager();
		sqlManager = new SqlManager();
	}

	public void newAction(Action action, int blockId) throws SQLException {
		String insertBlockQuery = mQueryManager.Insert("action", "actionId, type, blockId",
				action.getActionId() + "," + mQueryManager.toQueryStirng(action.getType()) + "," + blockId);
		sqlManager.excuteUpdate(insertBlockQuery);
	}

	public List<Action> getActions(int blockId) throws SQLException {
		List<Action> actions = new ArrayList<Action>();

		String selectQuery = mQueryManager.find("action", "blockId=" + blockId);
		ResultSet rs = sqlManager.excuteSelect(selectQuery);
		while (rs.next()) {
			actions.add(new Action(rs.getInt(1), rs.getString(2)));
		}
		return actions;
	}
}
