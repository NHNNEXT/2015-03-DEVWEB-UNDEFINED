package controller.scene;

import model.dao.ActionDao;
import model.dao.BlockDao;
import model.dao.SceneDao;
import model.json.JsonHandler;

public class SceneService {
	private JsonHandler jsonHandler;
	private SceneDao sceneDao;
	private BlockDao blockDao;
	private ActionDao actionDao;

	public SceneService() {
		jsonHandler = new JsonHandler();
		sceneDao = new SceneDao();
		blockDao = new BlockDao();
		actionDao = new ActionDao();
	}

	public void saveScene(String sceneData) {
		
	}

}
