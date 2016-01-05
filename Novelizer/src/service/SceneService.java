package service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.ActionDao;
import dao.BlockDao;
import dao.SceneDao;
import model.Block;
import model.Scene;
import utils.json.JsonHandler;

public class SceneService {
	private static final Logger log = LoggerFactory.getLogger(SceneService.class);

	private JsonHandler<Scene> jsonHandler;
	private BlockService blockService;
	private SceneDao sceneDao;
	private BlockDao blockDao;
	private ActionDao actionDao;

	public SceneService() {
		jsonHandler = new JsonHandler<>();
		sceneDao = new SceneDao();
		blockDao = new BlockDao();
		actionDao = new ActionDao();
		blockService = new BlockService();
	}

	public String saveScene(String sceneData) throws NullPointerException{
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

	public String getScene(int sceneId) throws SQLException {
		Scene scene = sceneDao.selectScene(sceneId);
		if (scene == null) {
			throw new NullPointerException();
		}

		scene.setBlockList(blockDao.selectBySceneId(scene.getSceneId()));
		for (Block block : scene.getBlockList()) {
			block.setActionList(actionDao.selectByBlockId((block.getBlockId())));
		}
		String json = jsonHandler.convertToJson(scene);
		log.info(json);
		return json;
	}

}
