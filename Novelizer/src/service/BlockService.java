package service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.BlockDao;
import model.block.Block;
import model.scene.Scene;

public class BlockService {
	private static final Logger log = LoggerFactory.getLogger(SceneService.class);
	private BlockDao blockDao;
	private ActionService actionService;

	public BlockService() {
		blockDao = new BlockDao();
		actionService = new ActionService();
	}

	public void saveBlock(Scene scene) throws SQLException {
		int sceneId = scene.getSceneId();
		for (Block block : scene.getBlockList()) {
			block.setSceneId(sceneId);
			blockDao.newBlock(block);
			actionService.saveAction(block);
		}
	}

}
