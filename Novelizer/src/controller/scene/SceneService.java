package controller.scene;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.dao.ActionDao;
import model.dao.BlockDao;
import model.dao.SceneDao;
import model.json.JsonHandler;
import vo.action.Action;
import vo.block.Block;
import vo.scene.Scene;

public class SceneService {
	private static final Logger log = LoggerFactory.getLogger(SceneService.class);

	private JsonHandler jsonHandler;
	private SceneDao sceneDao;
	private BlockDao blockDao;
	private ActionDao actionDao;

	// TODO datasource는 사용하지도 않고 있는데 전달하는 이유는?
	public SceneService(DataSource ds) {
		jsonHandler = new JsonHandler();
		sceneDao = new SceneDao();
		blockDao = new BlockDao();
		actionDao = new ActionDao();
	}

	public String saveScene(String sceneData) {
		Scene scene = jsonHandler.convertToScene(sceneData);
		try {
			if (sceneDao.hasScene(scene.getSceneId())) {
				return "Error : Scene already Exist";
			} else {
				sceneDao.newScene(scene);
				saveBlock(scene);
				return "result : DB INPUT Success";
			}
		} catch (SQLException e) {
			return "error : " + e;
		}
	}

	private void saveBlock(Scene scene) throws SQLException {
		int sceneId = scene.getSceneId();
		for (Block block : scene.getBlockList()) {
			blockDao.newBlock(block, sceneId);
			saveAction(block);
		}
	}

	private void saveAction(Block block) throws SQLException {
		int blockId = block.getBlockId();
		for (Action action : block.getActionList()) {
			log.info(action.toString());
			actionDao.newAction(action, blockId);
		}
	}

	// TODO sceneId type이 String이어도 괜찮은가? int로 변환한다면 어느 시점에서 변환하는 것이 좋은가?
	public String getScene(String sceneId) throws SQLException {
		Scene scene = sceneDao.getScene(sceneId);
		scene.setBlockList(blockDao.getBlocks(scene.getSceneId()));
		for (Block block : scene.getBlockList()) {
			block.setActionList(actionDao.getActions(block.getBlockId()));
		}
		return jsonHandler.convertToJson(scene);
	}

}
