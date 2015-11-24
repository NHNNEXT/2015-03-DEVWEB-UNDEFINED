package controller.scene;

import org.json.simple.JSONObject;

import model.dao.SceneDao;
import model.json.JsonHandler;

public class SceneService {
	private JsonHandler jsonHandler;
	private SceneDao sceneDao;
	private JSONObject scene;
	private long sceneId;
	
	public SceneService(){
		jsonHandler = new JsonHandler();
		sceneDao = new SceneDao();
	}
	
	public void saveScene(String sceneData){
		scene = jsonHandler.convertToJSONObject(sceneData);
		sceneId = (long) scene.get("sceneId");
		if(sceneDao.hasScene(sceneId)){
			sceneDao.updateScene(scene);
		}else{
			sceneDao.newScene(scene);
		}
	}
	
}
