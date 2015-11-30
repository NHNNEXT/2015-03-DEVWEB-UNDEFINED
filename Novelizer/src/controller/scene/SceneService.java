package controller.scene;

import java.sql.SQLException;

// TODO 사용하지 않는 import문은 제거한다. 
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.action.Action;
import model.action.ActionDao;
import model.block.Block;
import model.block.BlockDao;
import model.scene.Scene;
import model.scene.SceneDao;
import utils.json.JsonHandler;

public class SceneService {
	private static final Logger log = LoggerFactory.getLogger(SceneService.class);

	private JsonHandler jsonHandler;
	private SceneDao sceneDao;
	private BlockDao blockDao;
	private ActionDao actionDao;

	public SceneService() {
		jsonHandler = new JsonHandler();
		// TODO singleton 패턴은 JVM에 하나의 인스턴스만 만들고 싶다. 그런데 이와 같이 구현할 경우 여기서 1개의 인스턴스, ProjectController에도 하나의 인스턴스가 생성된다.
		// 인스턴스를 하나만 생성하고 싶다. 어떻게 구현하는 것이 좋을까? singleton 패턴 적용하면 될까?
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
				// TODO 이와 같이 반환하면 이 값을 사용할 것인가? 어떻게?
				return "result : DB INPUT Success";
			}
		} catch (SQLException e) {
			// TODO 이와 같이 반환하면 이 값을 사용할 것인가? 어떻게?
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
