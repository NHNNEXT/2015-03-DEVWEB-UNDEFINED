package controller.scene;

import javax.sql.DataSource;

import model.dao.ActionDao;
import model.dao.BlockDao;
import model.dao.SceneDao;
import model.json.JsonHandlerByJackson;
import vo.action.Action;
import vo.block.Block;
import vo.scene.Scene;

public class SceneService {
	private JsonHandlerByJackson jsonHandler;
	private SceneDao sceneDao;
	private BlockDao blockDao;
	private ActionDao actionDao;

	public SceneService(DataSource ds) {
		jsonHandler = new JsonHandlerByJackson();
		sceneDao = new SceneDao(ds);
		blockDao = new BlockDao(ds);
		actionDao = new ActionDao(ds);
	}

	public void saveScene(String sceneData) {
		Scene scene = jsonHandler.convertToObject(sceneData);
		if (!sceneDao.hasScene(scene.getSceneId())) {
			sceneDao.newScene(scene);
			saveBlock(scene);
		}
	}

	private void saveBlock(Scene scene) {
		int sceneId = scene.getSceneId();
		for (Block block : scene.getBlockList()) {
			blockDao.newBlock(block, sceneId);
			saveAction(block);
		}
	}

	private void saveAction(Block block) {
		int blockId = block.getBlockId();
		for (Action action : block.getActionList()){
			actionDao.newAction(action,blockId);
		}
	}

}
