package service;

import java.sql.SQLException;

import dao.BlockDao;
import model.Block;
import model.Scene;

public class BlockService {
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
			blockDao.insertBlock(block);
			actionService.saveAction(block);
		}
	}

}
