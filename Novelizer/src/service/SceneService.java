package service;

import java.sql.SQLException;

import dao.ActionDao;
import dao.BlockDao;
import dao.SceneDao;
import model.Block;
import model.Scene;
import utils.json.JsonHandler;

public class SceneService {
	private JsonHandler<Scene> jsonHandler;
	private BlockService blockService;
	private SceneDao sceneDao;
	private BlockDao blockDao;
	private ActionDao actionDao;

	public SceneService() {
		jsonHandler = new JsonHandler<>();
		sceneDao = new SceneDao();
		blockService = new BlockService();
	}

	public String saveScene(String sceneData) {
		Scene scene = jsonHandler.convertToScene(sceneData);
		scene.setProjectId(1);
		try {
			if (sceneDao.selectScene(scene.getSceneId()) != null) {
				return "Error : Scene already Exist";
			} else {
				int sceneId = sceneDao.insertScene(scene);
				blockService.saveBlock(scene);
				return "sceneId : " + sceneId;
			}
		} catch (Exception e) {
			return "error : " + e;
		}
	}

//	public String getScene(int sceneId) throws SQLException {
//		Scene scene = sceneDao.selectScene(sceneId);
//		if (scene == null) {
//			throw new NullPointerException();
//		}
//		scene.setBlockList(blockDao.selectBySceneId(scene.getSceneId()));
//		for (Block block : scene.getBlockList()) {
//			block.setActionList(actionDao.selectByBlockId((block.getBlockId())));
//		}
//		return jsonHandler.convertToJson(scene);
//	}

}
