package model.dao;

import org.json.simple.JSONObject;

public class SceneDao {

	public boolean hasScene(long sceneId) {
		String hasSecneQuery = "SELECT * FROM SCENE WHERE ID=" +sceneId + ";";
		return false;
	}

	public void updateScene(JSONObject scene) {
		// TODO Auto-generated method stub
		
	}

	public void newScene(JSONObject scene) {
		
	}

}
