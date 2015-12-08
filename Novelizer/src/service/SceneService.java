package service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.ActionDao;
import dao.BlockDao;
import dao.SceneDao;
import model.action.Action;
import model.block.Block;
import model.scene.Scene;
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
		// TODO singleton 패턴은 JVM에 하나의 인스턴스만 만들고 싶다. 그런데 이와 같이 구현할 경우 여기서 1개의
		// 인스턴스, ProjectController에도 하나의 인스턴스가 생성된다.
		// 인스턴스를 하나만 생성하고 싶다. 어떻게 구현하는 것이 좋을까? singleton 패턴 적용하면 될까?
		sceneDao = new SceneDao();
		blockDao = new BlockDao();
		actionDao = new ActionDao();
		
		blockService = new BlockService();
	}

	public String saveScene(String sceneData) {
		Scene scene = jsonHandler.convertToScene(sceneData);
		scene.setProjectId(1);
		try {
			if (sceneDao.hasScene(scene.getSceneId())) {
				return "Error : Scene already Exist";
			} else {
				sceneDao.newScene(scene);
				blockService.saveBlock(scene);
				// TODO 추후에 알맞은 데이터로 수정
				return "result : DB INPUT Success";
			}
		} catch (SQLException e) {
			// TODO 추후에 알맞은 데이터로 수정
			return "error : " + e;
		}
	}

	public String getScene(int sceneId) throws SQLException {
		Scene scene = sceneDao.getScene(sceneId);
		scene.setBlockList(blockDao.getBlocks(scene.getSceneId()));
		for (Block block : scene.getBlockList()) {
			block.setActionList(actionDao.getActions(block.getBlockId()));
		}
		return jsonHandler.convertToJson(scene);
	}

}
