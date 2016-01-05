package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Action;

public class ActionDao extends AbstractDao<Action> {
	private static final Logger log = LoggerFactory.getLogger(ActionDao.class);

	private static final String insertQuery = "INSERT INTO action(action_type, character_id, preset_id, option_id, posX, posY, animation, text, block_id) values(?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private final String selectByParentIdQuery = "SELECT * FROM action WHERE block_id = ?;";

	public ActionDao() {
		super.insertQuery = insertQuery;
		super.selectByParentIdQuery = selectByParentIdQuery;
	}

	public int insertAction(Action action) throws SQLException {
		try {
			ArrayList<Object> insertList = new ArrayList<Object>();

			insertList.add(action.getActionType());
			insertList.add(action.getCharacterId());
			insertList.add(action.getPresetId());
			insertList.add(action.getOptionId());
			insertList.add(action.getPosX());
			insertList.add(action.getPosY());
			insertList.add(action.getAnimation());
			insertList.add(action.getText());
			insertList.add(action.getBlockId());

			return insert(insertList);
		} catch (Exception e) {
			log.error("Insert Block Error");
			e.printStackTrace();
		}
		return -1;
	}

	public List<Action> selectByBlockId(int blockId) throws SQLException {
		List<List<Object>> actions = new ArrayList<>();
		List<Action> resulActions = new ArrayList<>();

		try {
			actions = selectByParentId(blockId);
			for (List<Object> actionContents : actions) {
				resulActions.add(convertToAction(actionContents));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resulActions;
	}

	private Action convertToAction(List<Object> actionContents) {
		int actionId = (Integer) actionContents.get(0);
		String type = (String) actionContents.get(1);
		int blockId = (Integer) actionContents.get(2);

		return new Action(actionId, type, blockId);
	}

	@Override
	public void update(Action vo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int key) throws Exception {
		// TODO Auto-generated method stub

	}
}
