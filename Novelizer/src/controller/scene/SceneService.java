package controller.scene;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.dao.ActionDao;
import model.dao.BlockDao;
import model.dao.SceneDao;
import model.json.JsonHandlerByJackson;
import vo.action.Action;
import vo.block.Block;
import vo.scene.Scene;

public class SceneService {
	private static final Logger log = LoggerFactory.getLogger(SceneService.class);

	private JsonHandlerByJackson jsonHandler;
	private SceneDao sceneDao;
	private BlockDao blockDao;
	private ActionDao actionDao;

	public SceneService(DataSource ds) {
		jsonHandler = new JsonHandlerByJackson();
		sceneDao = new SceneDao();
		blockDao = new BlockDao();
		actionDao = new ActionDao();
	}

	public String saveScene(String sceneData) {
		Scene scene = jsonHandler.convertToObject(sceneData);
		try {
			if (!sceneDao.hasScene(scene.getSceneId())) {
				sceneDao.newScene(scene);
				saveBlock(scene);
				return "result : DB INPUT Success";
			}
		} catch (SQLException e) {
			return "error : " + e;
		}
		return "result : Scene already Exist";
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

}
