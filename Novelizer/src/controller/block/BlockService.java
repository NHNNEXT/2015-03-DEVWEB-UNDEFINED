package controller.block;

import model.dao.BlockDao;

public class BlockService {
	private BlockDao dao;
	private BlockValidateChecker validateChecker;

	public BlockService() {
		dao = new BlockDao();
		validateChecker = new BlockValidateChecker();
	}

	public String getBlockList(int sceneId) {
		return dao.getBlockList();
	}

	public boolean isValidateBlockList(String jsonData) {
		return validateChecker.isValidate(jsonData);
	}
}
