package model.scene;

import java.util.ArrayList;
import java.util.List;

import model.ValueImpl;
import model.block.Block;

public class Scene implements ValueImpl {
	private int sceneId;
	private String sceneName;
	private List<Block> blockList;
	private int projectId;

	public Scene(int sceneId, String name) {
		this(sceneId,name,null,0);
	}

	public Scene(int sceneId, String sceneName, List<Block> blockList, int projectId) {
		super();
		this.sceneId = sceneId;
		this.sceneName = sceneName;
		this.blockList = blockList;
		this.projectId = projectId;
	}

	public int getSceneId() {
		return sceneId;
	}

	public int getProjectId() {
		return projectId;
	}

	public String getSceneName() {
		return sceneName;
	}

	public String getName() {
		return sceneName;
	}

	public List<Block> getBlockList() {
		return blockList;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public void setBlockList(List<Block> blockList) {
		this.blockList = blockList;
	}

	@Override
	public String toString() {
		return "Scene [sceneId=" + sceneId + ", name=" + sceneName + ", blockList=" + blockList + "]";
	}

	@Override
	public String getInsertQuery() {
		return "scene(sceneId, name, projectId) values(?,?,?);";
	}

	@Override
	public List<Object> getInsertList() {
		ArrayList<Object> insertList = new ArrayList<Object>();
		insertList.add(this.sceneId);
		insertList.add(this.sceneName);
		insertList.add(this.projectId);

		return insertList;
	}
}
