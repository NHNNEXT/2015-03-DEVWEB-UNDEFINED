package controller.block;

import model.dao.BlockDao;

public class BlockController {
	private BlockDao dao;

	public BlockController() {
		dao = new BlockDao();
	}

	public String getNextBlock(int sceneId, int blockId) {
		String data = "{sceneId : 3, blockId : 2}";
		findFlag(sceneId, blockId);

		return data;
	}

	private void findFlag(int sceneId, int blockId) {

	}

	public String getBlockList(int sceneId) {

		return dao.getBlockList();

	}
}
