package controller.block;

import model.dao.BlockDao;

public class BlockService {
	private BlockDao dao;

	public BlockService() {
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
