package model.dao;

import org.json.simple.JSONObject;

public class SceneDao {
	private SQLManager mSQLManager;
	
	public SceneDao(){
		mSQLManager = new SQLManager();
	}

	public boolean hasScene(long sceneId) {
		String hasSecneQuery = mSQLManager.find("scene", "ID="+sceneId);
		return false;
	}

	public void updateScene(JSONObject scene) {
		
	}

	public void newScene(JSONObject scene) {
		long sceneId = (long)scene.get("sceneId");
		long projectId = (long)scene.get("projectId");
		
	}
	
	public String getSceneList(){
		String getSceneListQuery = mSQLManager.findAll("scene");
		
		return null;
	}

}
